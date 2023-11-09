package automation.api.kanboard;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;

public class UserDeletingResponse {
    private String jsonrpc;
    private Boolean result;
    private Integer id;

    public UserDeletingResponse(){
        super();
    }

    public UserDeletingResponse(String jsonrpc, Integer id, Boolean result) {
        this.jsonrpc = jsonrpc;
        this.result = result;
        this.id = id;
    }

    public String getJsonrpc() {
        return jsonrpc;
    }

    public Boolean getResult() {
        return result;
    }

    public Integer getId() {
        return id;
    }
}
