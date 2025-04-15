package api.smell.bert.models;

public class JavaFile {
    private String name;
    private String path; // package
    private String code;

    public JavaFile(String name, String path, String code) {
        this.name = name;
        this.path = path;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
