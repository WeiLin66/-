#include <stdio.h>
#include <stdlib.h>
#include <stdint.h>
#include <stdbool.h>
#include <string.h>
#include <malloc.h>

typedef enum{
    String=0,
    Integer,
    Float,
    Boolean
}jsonType;

int StringIndexOf(char *str1, char *str2){
    if(strlen(str1) < strlen(str2))
        return -1 ;

    for (int i = 0; i < strlen(str1) - strlen(str2) + 1; i++){
        for (int index = 0; index < strlen(str2); index++){
            if(str1[index + i] != str2[index]){
                break;
            }

            if (index == strlen(str2) - 1){
                return i;
            }
        }
    }

    return -1;
}

char* JSONAnaylsis(char* jsonstr, char* tag, int arr_index, int len){
    int index = StringIndexOf(jsonstr, tag);
	
    if (index != -1){
        char* value;

        if (len == 0){
            value = calloc(32, sizeof(char));
        }else{
            value = calloc(len, sizeof(char));
        }
        
        if (value == NULL){
            return NULL;
        }

        int objectCount = 0;
        int arr_count = 0;
        uint8_t objectType = 0;

        if (jsonstr[index - 1] != '\"' ||
            jsonstr[index + strlen(tag)] != '\"' ||
            jsonstr[index + strlen(tag) + 1] != ':'){
            free(value);
            value = NULL;
            return NULL;
        }
				
        index += strlen(tag);
        index += 2;

        switch (jsonstr[index]){
            case '\"':                          
				objectType = 1; 
				index++; 
			break;
			
            case '{':                           
    			objectType = 2; 
    			index++; 
			break; 
			
            case '[':
                if (jsonstr[index + 1] == '{'){  
					objectType = 3;
                }else if (jsonstr[index + 1] == '\"'){
					objectType = 4;
                }else{                            
    				objectType = 5;
                }
				index++;
            break;
            
            default: 
				objectType = 0; 
			break;
        }

        for (int i = index; i < strlen(jsonstr); i++){
            switch (objectType){
                case 0:
                    if (jsonstr[i] == ',' || jsonstr[i] == '}'){
						*(value+strlen(value)) = '\0';
						value = (char*)realloc(value, strlen(value)+1);
                        return value;
                    }else{
						strncat(value, &jsonstr[i], 1);
					}
                break;

                case 1:
                    if (jsonstr[i] == '\"'){
						value[strlen(value)+1] = '\0';
						value = realloc(value, strlen(value) + 1);																
                        return value;
                    }else{
						strncat(value, &jsonstr[i], 1);
					}
                break;

                case 2:
                    if (jsonstr[i] == '}'){
                        if (objectCount == 0){
                            value = realloc(value, strlen(value) + 1);
                            return value;
                        }else{
                            objectCount--;
                        }
                    }

                    strncat(value, &jsonstr[i], 1);

                    if (jsonstr[i] == '{'){
                        objectCount++;
                    }
                break;

                case 3: // array-object
                    if (jsonstr[i] == ','){
                        if (objectCount == 0){
                            if (arr_count < arr_index){
                                arr_count++;
                                break;
                            }else{
                                value = realloc(value, strlen(value) + 1);
                                return value;
                            }
                        }
                    }

                    if (jsonstr[i] == ']'){
                        if (objectCount == 0){
                            value = realloc(value, strlen(value) + 1);
                            return value;
                        }else{
                            objectCount--;
                        }
                    }
                    
                    if (arr_count == arr_index){
                        strncat(value, &jsonstr[i], 1);
                    }
                    if (jsonstr[i] == '{' || jsonstr[i] == '['){
                        objectCount++;
                    }else if (jsonstr[i] == '}'){
                        objectCount--;
                    }
                break;

                 case 4:
                    if (jsonstr[i] == ','){
                        if (objectCount == 0){
                            if (arr_count < arr_index){
                                arr_count++;
                                break;
                            }else{
                                value = realloc(value, strlen(value) + 1);
                                return value;
                            }
                        }
                    }

                    if (jsonstr[i] == ']'){
                        if (objectCount == 0){
                            value = realloc(value, strlen(value) + 1);
                            return value;
                        }else{
                            objectCount--;
                        }
                    }

                    if (jsonstr[i] == '\"'){
                        if (objectCount == 0){
                            objectCount = 1;
                        }else{
                            objectCount = 0;
                        }
                    }else if (arr_count == arr_index){
                        strncat(value, &jsonstr[i], 1);
                    }
                break;

                case 5:
                    if (jsonstr[i] == ','){
                        if (arr_count < arr_index){
                            arr_count++;
                            break;
                        }else{
                            value = realloc(value, strlen(value) + 1);
                            return value;
                        }
                    }

                    if (jsonstr[i] == ']'){
                        value = realloc(value, strlen(value) + 1);
                        return value;
                    }
                    
                    if (arr_count == arr_index && jsonstr[i] != '\"'){
                        strncat(value, &jsonstr[i], 1);
                    }
                break;
            }
        }
							
        free(value);
		value = NULL;
    }

    return NULL;
}

/**
 * This function is uesd to find the specific value pair with its key(ID)
 * @param payload Json String
 * @param len Json data length
 * @param ID Json ID pair
 * @return return specific value in char*(if ID exist), otherwise return NULL
 * @author Zach Lin 
 */
char* json_format_string_parse(char* payload, uint32_t len, const char* ID){
    int index = 0;

    while (index < len){
      if (payload[index] == '{'){
		char* id = JSONAnaylsis((payload + index), "id", 0, 0);
		char* value = JSONAnaylsis((payload + index), "value", 0, 0);
			
        if (value != NULL && strlen(value) > 0 && strcmp(id, ID) == 0){
            free(id);
            id = NULL;
            return value;
        }
        free(value);
        value = NULL;
      }

      index++;
    }
    return NULL;
}

/**
 * A simple Json value Generics getter, use type to determine
 * return data type
 * @param str Json String
 * @param len Json data length
 * @param ID Json ID pair
 * @param type data types, refer to enum jsonType
 * @return a void* type pointer, so casting is definitely needed
 * @author Zach Lin 
 */
void* json_value_getter(char* str, int len, char* ID, int type){
    void* value=NULL;
    int intValue;
    float floatValue;

    switch(type){
        case String:
            value = (char*)json_format_string_parse(str, len, "GW_42634");
        break;
            
        case Integer:
            value = (char*)json_format_string_parse(str, len, "GW_42634");
            sscanf(value, "%d", &intValue);
            free(value);
            value = &intValue;
        break;
        
        case Float:
            value = (char*)json_format_string_parse(str, len, "GW_42634");
            sscanf(value, "%f", &floatValue);
            free(value);
            value = &floatValue;
        break;
        
        case Boolean:
            value = (char*)json_format_string_parse(str, len, "GW_42634");
            sscanf(value, "%d", &intValue);
            free(value);
            *(int*)value = (intValue == 0) ? false : true;
        break;
        
        default:
            return NULL;
        break;
    }
    return value;
}

/**
 * 
 * 
 * 
 */
void json_value_setter(char* ID, void* value, char* topic, int type){
    switch(type){
        case String:
            // IOTAddJsonString(ID, "", (char*)value, topic, 0);
        break;
        
        case Integer:
            // IOTAddJsonInt(ID, "", *(int*)value, topic, 0);
        break;
        
        case Float:
            // IOTAddJsonFloat(ID, "", *(float*)value, topic, 0);
        break;
        
        case Boolean:
            // IOTAddJsonInt(ID, "", *(int*)value, topic, 0);
        break;
        
        default:
        break;
    }
}

int main(){
    char* str = "[{\"id\":\"GW_42632\",\"value\":25},{\"id\":\"GW_42633\",\"value\":0},{\"id\":\"GW_42634\",\"value\":15.3}]";
    int len = strlen(str);
    
    printf("str = %f\n\n", *((float*)json_value_getter(str, len, "GW_42634", Float)));
    
    return 0;
}
