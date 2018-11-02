package cn.kgc.entity;

public class Homework {
	
	private String id;
	private Integer notescore;
	private Integer code_number;
	private Integer isannotate; 
    private double correct_rate;
    private String homework_time;
    private String studentName;
    private String question;
    private String team;
    public Homework(){
    	
    }
    
	public Homework(String id, int notescore, int code_number,int isannotate, double correct_rate,String homework_time,String studentName,String question,String team) {
		// TODO Auto-generated constructor stub
		this.id=id;
		this.notescore=notescore;
		this.code_number=code_number;
		this.isannotate=isannotate;
		this.correct_rate=correct_rate;
		this.homework_time=homework_time;
		this.studentName=studentName;
		this.question=question;
		this.team=team;
	}

	@Override
	public String toString() {
		return "HomeWork [id=" + id + ",  notescore=" + notescore + ", code_number=" + code_number
				+ ", isannotate=" + isannotate + ", correct_rate="
				+ correct_rate + "]";
	}
    
	public String getHomework_time() {
		return homework_time;
	}

	public void setHomework_time(String homework_time) {
		this.homework_time = homework_time;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public Integer getNotescore() {
		return notescore;
	}
	public void setNotescore(Integer notescore) {
		this.notescore = notescore;
	}
	public Integer getCode_number() {
		return code_number;
	}
	public void setCode_number(Integer code_number) {
		this.code_number = code_number;
	}
	public int getIsannotate() {
		return isannotate;
	}
	public void setIsannotate(int isannotate) {
		this.isannotate = isannotate;
	}
	public double getCorrect_rate() {
		return correct_rate;
	}
	public void setCorrect_rate(double correct_rate) {
		this.correct_rate = correct_rate;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public void setIsannotate(Integer isannotate) {
		this.isannotate = isannotate;
	}
	
}
