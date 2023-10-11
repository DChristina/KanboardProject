package automation.api.kanboard;

public class UserCreationResponse {
    private String jsonrpc;
    private String result;
    private Integer id;

    public UserCreationResponse(String jsonrpc, String result, Integer id){
        this.id = id;
        this.jsonrpc = jsonrpc;
        this.result = result;
    }
    public UserCreationResponse(){
        super();
    }

    public String getJsonrpc() {
        return jsonrpc;
    }

    public String getResult() {
        return result;
    }

    public Integer getId() {
        return id;
    }
}
