package automation.api.kanboard;

public class ProjectCreationResponse {
    private String jsonrpc;
    private Integer id;
    private Integer result;

    public ProjectCreationResponse(String jsonrpc, Integer id, Integer result) {
        this.jsonrpc = jsonrpc;
        this.id = id;
        this.result = result;
    }

    public ProjectCreationResponse() {
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
