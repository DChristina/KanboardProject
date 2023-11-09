package automation.api.kanboard;

public class TaskDeletingResponse {
    private String jsonrpc;
    private Boolean result;
    private Integer id;

    public TaskDeletingResponse(){
        super();
    }

    public TaskDeletingResponse(String jsonrpc, Integer id, Boolean result) {
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
