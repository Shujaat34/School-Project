package com.school.bean;

public class ProjectBean {
		private String id; 
		private String project;
		private String duration;
		
		public ProjectBean( String project, String duration) {
			this.project = project;
			this.duration = duration;
		}
	
		public ProjectBean(String id, String project, String duration) {
			super();
			this.id = id;
			this.project = project;
			this.duration = duration;
		}

		public String getId() {
			return id;
		}

		public String getProject() {
			return project;
		}

		public String getDuration() {
			return duration;
		}
		
		
		
	
		
		
}
