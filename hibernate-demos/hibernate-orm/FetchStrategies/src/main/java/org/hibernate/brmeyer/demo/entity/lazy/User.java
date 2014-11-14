package org.hibernate.brmeyer.demo.entity.lazy;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.NaturalId;

@Entity
public class User {
	
	@Id
	@GeneratedValue
	private int id;

	private String address1;

	private String address2;

	private String city;

	@NaturalId
	private String email;

	private String firstName;

	private String lastName;

	private String phone;

	private String state;

	private String zip;
	
	@ManyToMany
	private List<Community> communityMemberships = new ArrayList<Community>();
	
	@OneToMany(mappedBy = "creator")
	@BatchSize(size = 5)
	private List<Community> communitiesCreated = new ArrayList<Community>();
	
	@ManyToMany
	@Fetch(FetchMode.SUBSELECT)
	private List<Skill> skills = new ArrayList<Skill>();
	
	@ManyToMany
	private List<Tool> tools = new ArrayList<Tool>();
	
	@OneToMany(mappedBy = "user")
	private List<Donation> donations = new ArrayList<Donation>();
	
	@OneToMany(mappedBy = "submitter")
	@OrderColumn(name = "projectsSubmitted_index")
	private List<Project> projectsSubmitted = new ArrayList<Project>();
	
	@OneToMany(mappedBy = "organizer")
	@OrderColumn(name = "projectsOrganized_index")
	private List<Project> projectsOrganized = new ArrayList<Project>();
	
	@ManyToMany(mappedBy = "volunteers")
	private List<Project> projectsVolunteered = new ArrayList<Project>();
	
	@OneToMany(mappedBy = "submitter")
	@LazyCollection(LazyCollectionOption.EXTRA)
	@OrderColumn(name = "comments_index") // Index necessary to know which element to lazy-init!
	private List<Comment> comments = new ArrayList<Comment>();
	
	@OneToMany(mappedBy = "organizer")
	private List<ServiceEvent> serviceEventsOrganized = new ArrayList<ServiceEvent>();
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getAddress1() {
		return address1;
	}
	
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	
	public String getAddress2() {
		return address2;
	}
	
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public String getZip() {
		return zip;
	}
	
	public void setZip(String zip) {
		this.zip = zip;
	}
	
	public List<Community> getCommunityMemberships() {
		return communityMemberships;
	}
	
	public void setCommunityMemberships(List<Community> communityMemberships) {
		this.communityMemberships = communityMemberships;
	}
	
	public List<Community> getCommunitiesCreated() {
		return communitiesCreated;
	}
	
	public void setCommunitiesCreated(List<Community> communitiesCreated) {
		this.communitiesCreated = communitiesCreated;
	}
	
	public List<Skill> getSkills() {
		return skills;
	}
	
	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}
	
	public List<Tool> getTools() {
		return tools;
	}
	
	public void setTools(List<Tool> tools) {
		this.tools = tools;
	}
	
	public List<Project> getProjectsSubmitted() {
		return projectsSubmitted;
	}
	
	public void setProjectsSubmitted(List<Project> projectsSubmitted) {
		this.projectsSubmitted = projectsSubmitted;
	}
	
	public List<Project> getProjectsOrganized() {
		return projectsOrganized;
	}
	
	public void setProjectsOrganized(List<Project> projectsOrganized) {
		this.projectsOrganized = projectsOrganized;
	}
	
	public List<Project> getProjectsVolunteered() {
		return projectsVolunteered;
	}
	
	public void setProjectsVolunteered(List<Project> projectsVolunteered) {
		this.projectsVolunteered = projectsVolunteered;
	}
	
	public List<Comment> getComments() {
		return comments;
	}
	
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	public List<Donation> getDonations() {
		return donations;
	}
	
	public void setDonations(List<Donation> donations) {
		this.donations = donations;
	}
	
	public List<ServiceEvent> getServiceEventsOrganized() {
		return serviceEventsOrganized;
	}
	
	public void setServiceEventsOrganized(List<ServiceEvent> serviceEventsOrganized) {
		this.serviceEventsOrganized = serviceEventsOrganized;
	}
}
