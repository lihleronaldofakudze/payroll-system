public class Job {
    int job_id;
    String job_title;
    String job_type;
    String job_description;

    public Job(int job_id, String job_title, String job_type, String job_description) {
        this.job_id = job_id;
        this.job_title = job_title;
        this.job_type = job_type;
        this.job_description = job_description;
    }
}