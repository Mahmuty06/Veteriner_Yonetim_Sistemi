package patika.dev.veterineryonetimsistemi.core.utilies;

import patika.dev.veterineryonetimsistemi.core.result.ResultData;

public class ResultHelper {

    public static <T>ResultData<T>created(T data){
       return new ResultData<>(true,Msg.CREATED,"201",data);

    }

}
