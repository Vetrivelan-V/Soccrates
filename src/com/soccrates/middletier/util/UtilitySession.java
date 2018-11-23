package com.soccrates.middletier.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import com.soccrates.middlertier.mail.MailSettingEntity;
import com.soccrates.middletier.address.AddressEntity;
import com.soccrates.middletier.company.CompanyEntity;
import com.soccrates.middletier.evaluation.EvaluationEntity;
import com.soccrates.middletier.evaluation.SuperEvaluationEntity;
import com.soccrates.middletier.message.MessageEntity;
import com.soccrates.middletier.message.RecipientEntity;
import com.soccrates.middletier.schedule.RSVPEntity;
import com.soccrates.middletier.schedule.ReminderBO;
import com.soccrates.middletier.schedule.ReminderEntity;
import com.soccrates.middletier.schedule.RsvpBO;
import com.soccrates.middletier.schedule.ScheduleBO;
import com.soccrates.middletier.schedule.ScheduleEntity;
import com.soccrates.middletier.schedule.ScheduleListBO;
import com.soccrates.middletier.schedule.ScheduleSearchBO;
import com.soccrates.middletier.schedule.ScheduleWebServices;
import com.soccrates.middletier.team.TeamBO;
import com.soccrates.middletier.team.TeamEntity;
import com.soccrates.middletier.team.TeamListBO;
import com.soccrates.middletier.team.TeamPlayerBO;
import com.soccrates.middletier.team.TeamPlayerEntity;
import com.soccrates.middletier.team.TeamSearchBO;
import com.soccrates.middletier.team.TeamWebServices;
import com.soccrates.middletier.template.CategoryEntity;
import com.soccrates.middletier.template.TemplateEntity;
import com.soccrates.middletier.user.UserBO;
import com.soccrates.middletier.user.UserDeviceEntity;
import com.soccrates.middletier.user.UserEntity;
import com.soccrates.middletier.user.UserEntityHandler;

// TODO: Auto-generated Javadoc
/**
 * The Class UtilityTaxEngine.
 */
public class UtilitySession {

	public static String HIBERNATE_IP;
	public static String HIBERNATE_USERNAME;
	public static String HIBERNATE_PASSWORD;
	public static String HIBERNATE_SCHEMA;

	static {
		UtilitySession.getSession();
	}
	/** The factory. */
	private static SessionFactory factory = null;

	/**
	 * Gets the session.
	 *
	 * @return the session
	 */
	public static Session getSession() {
		if (factory == null) {
			init();
		}
		Session session = factory.openSession();
		return session;
	}

	public static void main(String[] args) {
		// UtilitySession.getSession();
		// dummyCoachCreate();
		// checkUser();
		// SendMail sendMail = new SendMail();
		// try {
		// SendMail.sendExceptionMail("Welcome to Soccrates", "Hi All",
		// "t2vetri@gmail.com", UtilitySession.getSession());
		// } catch (SoccratesException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// getTeamById();
//		dummtyTeamCreate();
//		getTeamList();
//		invitePlayer();
//		getplayerTeamList();
//		getTeamPlayers();
//		createSchedule();
//		createReminder();
	//	createRsvp();
		getList();
	}
	public static void getList() {
		ScheduleWebServices scheduleWebServices = new ScheduleWebServices();
		ScheduleSearchBO userBO= new ScheduleSearchBO();
		userBO.setCoachId(10);
		userBO.setLoadReminder(true);
		userBO.setLoadRsvp(true);
		userBO.setStartDate("01/01/2018 12:05:58");
		userBO.setEndDate("01/01/2019 12:05:58");
		ScheduleListBO userBOList = scheduleWebServices.getScheduleByDateUserId(userBO);
		System.err.println(userBOList.toString());
	}

	public static void createRsvp() {
		ScheduleWebServices scheduleWebServices = new ScheduleWebServices();
		RsvpBO userBO= new RsvpBO();
		userBO.setScheduleId(1);
		userBO.setAttendance(10);
		userBO.setScheduleId(1);
		userBO.setUserId(10);
		userBO=scheduleWebServices.createRsvp(userBO);
		System.err.println(userBO.toString());
	}
	public static void createSchedule() {
		ScheduleWebServices scheduleWebServices = new ScheduleWebServices();
		ScheduleBO userBO= new ScheduleBO();
		userBO.setScheduleId(2);
		userBO.setCoachId(10);
		userBO.setTeamId(2);
		userBO.setComments("Test");
		userBO=scheduleWebServices.createSchedule(userBO);
		System.err.println(userBO.toString());
	}

	public static void createReminder() {
		ScheduleWebServices scheduleWebServices = new ScheduleWebServices();
		ReminderBO userBO= new ReminderBO();
		userBO.setScheduleId(1);
		userBO.setUserId(10);
		userBO.setScheduledDate("01/01/2018 12:05:58");
		userBO.setStartDate("01/01/2018 12:05:58");
		userBO=scheduleWebServices.createReminder(userBO);
		System.err.println(userBO.toString());
	}

	public static void getTeamPlayers() {
		TeamWebServices teamWebServices = new TeamWebServices();
		TeamSearchBO teamSearchBO = new TeamSearchBO();
		teamSearchBO.setTeamId(5);
		teamWebServices.getTeamPlayeList(teamSearchBO);
	}

	public static void getplayerTeamList() {
		TeamWebServices teamWebServices = new TeamWebServices();
		TeamSearchBO teamSeachBO = new TeamSearchBO();
		teamSeachBO.setPlayerId(16);
		teamWebServices.getPlayerTeamList(teamSeachBO);
	}

	public static void invitePlayer() {
		TeamWebServices teamWebServices = new TeamWebServices();
		TeamPlayerBO teamPlayerBO = new TeamPlayerBO();
		teamPlayerBO.setEmailId("vetrivelanv1988@gmail.com");
		teamPlayerBO.setTeamId(5);
		System.err.println(teamWebServices.invitePlayer(teamPlayerBO));
	}

	public static void getTeamById() {
		TeamWebServices teamWebServices = new TeamWebServices();
		TeamSearchBO userBO = new TeamSearchBO();
		userBO.setTeamId(1);
		System.err.println(teamWebServices.getTeamById(userBO));
	}

	public static void getTeamList() {
		TeamWebServices teamWebServices = new TeamWebServices();
		TeamSearchBO teamSeachBO = new TeamSearchBO();
		teamSeachBO.setCoachId(10);
		TeamListBO list = teamWebServices.getTeamList(teamSeachBO);
		for (int i = 0; i < list.getTeamList().size(); i++)
			System.err.println(list.getTeamList().get(i));
	}

	public static void checkUser() {
		UserBO userBO = new UserBO();
		userBO.setLoginId("t2vetri@gmail.com");
		userBO.setPassword("Welcome@123");
		UserEntityHandler userEntityHandler = new UserEntityHandler();
		try {
			System.err.println(userEntityHandler.login(userBO));
		} catch (SoccratesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void dummtyTeamCreate() {
		TeamBO teamBO = new TeamBO();
		teamBO.setAgeGroup("UnderAge");
		UserBO coach = new UserBO();
		coach.setUserId(10);
		teamBO.setCoach(coach);
		teamBO.setGender("Co-ed");
		teamBO.setTeamName("Demo Team");
//	teamBO.set
		TeamWebServices teamWebServices = new TeamWebServices();
		System.err.println(teamWebServices.createTeam(teamBO));
	}

	public static void dummyCoachCreate() {
		UserEntityHandler userEntityHandler = new UserEntityHandler();
		UserBO userBo = new UserBO();
		userBo.setActive(true);
		userBo.setCoach(true);
		userBo.setCompanyId(1);

		userBo.setEmailId("aalimmulji@gmail.com");// Aalim Sandeep Sam
		userBo.setFirstName("Aalim");
		userBo.setLastName("Mulji");
		userBo.setNickName("Aalim");
		userBo.setAbout_Desc("This is a demo Coach");

		userBo.setPassword("Welcome@123");

		for (int i = 0; i < 1; i++) {

			try {
				userEntityHandler.createUser(userBo);
			} catch (SoccratesException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/**
	 * Inits the.
	 */
	private static void init() {
		// TODO Auto-generated method stub
		AnnotationConfiguration configuration = new AnnotationConfiguration();
		configuration.addAnnotatedClass(AddressEntity.class);
		configuration.addAnnotatedClass(CompanyEntity.class);
		configuration.addAnnotatedClass(SettingsEntity.class);
		configuration.addAnnotatedClass(UserEntity.class);
		configuration.addAnnotatedClass(TeamEntity.class);
		configuration.addAnnotatedClass(TeamPlayerEntity.class);
		configuration.addAnnotatedClass(MailSettingEntity.class);
		configuration.addAnnotatedClass(CategoryEntity.class);
		configuration.addAnnotatedClass(TemplateEntity.class);
		configuration.addAnnotatedClass(EvaluationEntity.class);
		configuration.addAnnotatedClass(ScheduleEntity.class);
		configuration.addAnnotatedClass(ReminderEntity.class);
		configuration.addAnnotatedClass(MessageEntity.class);
		configuration.addAnnotatedClass(UserDeviceEntity.class);
		configuration.addAnnotatedClass(RecipientEntity.class);
		configuration.addAnnotatedClass(RSVPEntity.class);
		configuration.addAnnotatedClass(SuperEvaluationEntity.class);
		configuration.configure("hibernate.cfg.xml");
		HIBERNATE_PASSWORD = configuration.getProperty("hibernate.connection.password");
		HIBERNATE_USERNAME = configuration.getProperty("hibernate.connection.username");
		HIBERNATE_SCHEMA = configuration.getProperty("hibernate.default_schema");
		HIBERNATE_IP = configuration.getProperty("hibernate.connection.url");
		HIBERNATE_IP = HIBERNATE_IP.substring(HIBERNATE_IP.indexOf("://") + 3, HIBERNATE_IP.indexOf(":3306"));
		factory = configuration.buildSessionFactory();
	}

}
