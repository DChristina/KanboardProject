package automation.api.kanboard;

public class ProjectDeletingResponse {
    private String jsonrpc;
    private Boolean result;
    private Integer id;

    public ProjectDeletingResponse(){
        super();
    }

    public ProjectDeletingResponse(String jsonrpc, Integer id, Boolean result) {
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
