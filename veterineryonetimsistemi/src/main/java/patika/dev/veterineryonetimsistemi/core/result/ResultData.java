package patika.dev.veterineryonetimsistemi.core.result;

import lombok.Getter;

@Getter
public class ResultData <T> extends Result{
    private T data;
    public ResultData(Boolean status,String message, String code,T data) {
        super(status,message,code);
        this.data= data;

    }

}
