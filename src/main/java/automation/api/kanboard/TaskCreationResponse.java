package automation.api.kanboard;

public class TaskCreationResponse {
    private String jsonrpc;
    private Integer id;
    private Integer result;

    public TaskCreationResponse(String jsonrpc, Integer id, Integer result) {
        this.jsonrpc = jsonrpc;
        this.id = id;
        this.result = result;
    }

    public TaskCreationResponse() {
        super();
    }

    public String getJsonrpc() {
        return jsonrpc;
    }

    public Integer getId() {
        return id;
    }

    public Integer getResult() {
        return result;
    }

    public void setJsonrpc(String jsonrpc) {
        this.jsonrpc = jsonrpc;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setResult(Integer result) {
        this.result = result;
    }
}
