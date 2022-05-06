import java.io.Serializable;

public class Command implements Serializable {
    private String cm_name;
    private LabWork labWork;
    private String script_data;

    public String getCm_name() {
        return cm_name;
    }

    public void setCm_name(String cm_name) {
        this.cm_name = cm_name;
    }

    public LabWork getLabWork() {
        return labWork;
    }

    public void setLabWork(LabWork labWork) {
        this.labWork = labWork;
    }

    public String getScript_data() {
        return script_data;
    }

    public void setScript_data(String script_data) {
        this.script_data = script_data;
    }
}
