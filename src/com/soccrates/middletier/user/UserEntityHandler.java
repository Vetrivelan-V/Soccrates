package com.soccrates.middletier.user;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.soccrates.middlertier.mail.SendMail;
import com.soccrates.middletier.company.CompanyEntity;
import com.soccrates.middletier.constant.ApplicationConstants;
import com.soccrates.middletier.util.AESencrp;
import com.soccrates.middletier.util.SettingsEntity;
import com.soccrates.middletier.util.SoccratesException;
import com.soccrates.middletier.util.SoccratesExceptionCode;
import com.soccrates.middletier.util.UtilityLogger;
import com.soccrates.middletier.util.UtilitySession;

public class UserEntityHandler {
	private static SecureRandom random = new SecureRandom();
	private static Logger logger = UtilityLogger.getLog(UserEntityHandler.class.getSimpleName());

	public static String nextSessionId() {

		return new BigInteger(130, random).toString(32);
	}

	public long checkUniqueLoginId(UserBO userBO) throws SoccratesException {
		logger.entering(this.getClass().getName(), "checkUniqueLoginId()", new Object[] { userBO });

		Session session = UtilitySession.getSession();
		session.beginTransaction();
		long id = 0;
		try {
			if (userBO == null) {
				throw new SoccratesException(SoccratesExceptionCode.USERNOTFOUND);
			}
			Query query = null;

			query = session.createQuery("select userId from UserEntity where trim(loginId) like :loginId ");
			query.setString("loginId", userBO.getLoginId());

			query.setMaxResults(1);
			Object obj = query.uniqueResult();
			if (obj != null) {
				id = (Long) obj;
			}

		} catch (HibernateException exception) {
			logger.log(Level.SEVERE, "Uncaught exception", exception);
			exception.printStackTrace();
			throw new SoccratesException(exception.getMessage(), SoccratesExceptionCode.HIBERNATEEXCEPION);
		} finally {
			logger.exiting(this.getClass().getName(), "checkUniqueLoginId()", id);
			if (session.isOpen()) {
				session.close();
			}
		}
		return id;

	}

	@SuppressWarnings("unchecked")
	public ArrayList<UserBO> getListOfPlayersForCoach(UserBO userBO) throws SoccratesException {
		logger.entering(this.getClass().getName(), "checkUniqueLoginId()", new Object[] { userBO });

		Session session = UtilitySession.getSession();
		session.beginTransaction();
		ArrayList<UserBO>userBOs= new ArrayList<>();
		try {
			if (userBO == null||userBO.getCoachId()==0) {
				throw new SoccratesException(SoccratesExceptionCode.USERNOTFOUND);
			}
			Query query = null;

			query = session.createQuery(" from UserEntity where createdBy.userId = :userId ");
			query.setLong("userId", userBO.getCoachId());

			List<UserEntity>list=query.list();
			
			for (UserEntity userEntity : list) {
				UserBO user= new UserBO();
				user.copy(userEntity, userEntity.getCompanyId().getSettingsId().getProfileImagePath());
				userBOs.add(user);
			}
			
		} catch (HibernateException exception) {
			logger.log(Level.SEVERE, "Uncaught exception", exception);
			exception.printStackTrace();
			throw new SoccratesException(exception.getMessage(), SoccratesExceptionCode.HIBERNATEEXCEPION);
		} finally {
			logger.exiting(this.getClass().getName(), "checkUniqueLoginId()");
			if (session.isOpen()) {
				session.close();
			}
		}
		  return userBOs;

	}

	public UserBO login(UserBO userBO) throws SoccratesException

	{
		Session session = (Session) UtilitySession.getSession();
		session.beginTransaction();
		//UserBO loginBO = new UserBO();

		try {

			if (logger.isLoggable(Level.FINER)) {
				logger.entering(this.getClass().getName(), "Login()", new Object[] { userBO });
			}
			org.hibernate.Query query = session.createQuery("from UserEntity where loginId like :loginId");
			query.setString("loginId", userBO.getLoginId().trim());
			query.setMaxResults(1);
			UserEntity userEntity = (UserEntity) query.uniqueResult();

			if (userEntity != null) {
				try {
					SettingsEntity settingsEntity = (SettingsEntity) session.get(SettingsEntity.class,
							userEntity.getCompanyId().getSettingsId().getSettingsId());
					if (userEntity.getPassword().equals(AESencrp.encrypt(userBO.getPassword()))) {
						userBO.copy(userEntity, settingsEntity.getUrlPath());
						boolean store=false;
						if(userBO.getUserDeviceBO()!=null&&userBO.getUserDeviceBO().getDeviceKey()!=null&&userBO.getUserDeviceBO().getDeviceKey().trim().length()>0)
						{
								org.hibernate.Query userDeviceQuery = session.createQuery("from UserDeviceEntity where deviceKey = :deviceKey and ownerId.userId =:userId");
								userDeviceQuery.setLong("userId", userEntity.getUserId());
								userDeviceQuery.setString("deviceKey", userBO.getUserDeviceBO().getDeviceKey().trim());
								userDeviceQuery.setMaxResults(1);
								UserDeviceEntity userDeviceEntity = (UserDeviceEntity) userDeviceQuery.uniqueResult();
								if(userDeviceEntity==null)
								{
									store=true;	
								}
								
							}
						if(store)
						{
							UserDeviceEntity userDeviceEntity = new UserDeviceEntity();
							userDeviceEntity.setActive(true);
							userDeviceEntity.setOwnerId(userEntity);
							userDeviceEntity.setDeviceKey(userBO.getUserDeviceBO().getDeviceKey());
							userDeviceEntity.setUserDeviceType(userBO.getUserDeviceBO().getUserDeviceType());
							session.save(userDeviceEntity);
							session.getTransaction().commit();
						}
					} else {
						throw new SoccratesException(SoccratesExceptionCode.INVALIDUSERNAMEORPASSWORD);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					logger.log(Level.SEVERE, "Uncaught exception", e);
					e.printStackTrace();
					throw new SoccratesException(SoccratesExceptionCode.INVALIDUSERNAMEORPASSWORD);
				}

			} else {
				throw new SoccratesException(SoccratesExceptionCode.INVALIDUSERNAMEORPASSWORD);
			}

		} catch (SoccratesException e) {
			logger.log(Level.SEVERE, "Uncaught exception", e);
			throw e;
		} catch (HibernateException exception) {
			exception.printStackTrace();
			logger.log(Level.SEVERE, "Uncaught exception", exception);
			throw new SoccratesException(SoccratesExceptionCode.HIBERNATEEXCEPION);
		} finally {
			logger.exiting(this.getClass().getName(), "login()", userBO);
			if (session.isOpen()) {
				session.close();
			}
		}
		return userBO;

	}

	public UserBO createUser(UserBO userBo) throws SoccratesException {
		Session session = UtilitySession.getSession();
		session.beginTransaction();
		try {

			logger.entering(this.getClass().getName(), "createUser()", userBo);
			if (checkUniqueEmailId(userBo.getEmailId()) > 0) {
				throw new SoccratesException(SoccratesExceptionCode.EMAILIDPRESENT);
			}
			CompanyEntity companyId = (CompanyEntity) session.get(CompanyEntity.class, userBo.getCompanyId());
			if (companyId != null) {
				UserEntity userEntity = new UserEntity();
				userEntity.setActive(true);
				userEntity.setCreationDate(new Date());
				userEntity.setEmailId(userBo.getEmailId());

				userEntity.setCompanyId(companyId);
				userEntity.setFirstName(userBo.getFirstName());
				userEntity.setEmailId(userBo.getEmailId());
				userEntity.setLastName(userBo.getLastName());
				userEntity.setDateOfBirth((userBo.getDateOfBirth() != null)
						? ApplicationConstants.US_DATE_FORMAT.parse(userBo.getDateOfBirth())
						: null);
				userEntity.setLoginId(userBo.getEmailId());
				userEntity.setPhoneNumber(userBo.getPhoneNumber());
				userEntity.setPassword(AESencrp.encrypt(userBo.getPassword()));
				userEntity.setUpdationDate(new Date());
				
				userEntity.setPlayerNumber(userBo.getPlayerNumber());
				userEntity.setPosition(userBo.getPosition());
				userEntity.setFavPlayer(userBo.getFavPlayer());
				String mailContent = "";
				if (userBo.getCoachId() > 0) {
					// Creating Player
					// mailContent =
					mailContent = (companyId.getSettingsId().getPlayerRegistrationContent());
					if (mailContent != null) {
						mailContent = mailContent.replace("#{name}",
								((userBo.getFirstName() != null) ? userBo.getFirstName() : "Soccrates User") + " "
										+ ((userBo.getLastName() != null) ? userBo.getLastName() : ""));
						mailContent = mailContent.replace("#{loginId}", userEntity.getLoginId());
						mailContent = mailContent.replace("#{password}", "Welcome@123");
						mailContent = mailContent.replace("#{admin}", "Soccrates Admin");

					}
					userEntity.setActive(false);
					userEntity.setUserType(ApplicationConstants.USERTYPE_PLAYER);
					UserEntity coachEntity = (UserEntity) session.get(UserEntity.class, userBo.getCoachId());
					if (coachEntity == null)
						throw new SoccratesException(SoccratesExceptionCode.COACHNOTFOUND);
					else {
						
						userEntity.setCreatedBy(coachEntity);
					}

				} else {
					// Creating Coach
					userEntity.setActive(true);
					mailContent = (companyId.getSettingsId().getCoachRegistrationContent());
					if (mailContent != null) {
						mailContent = mailContent.replace("#{name}",
								((userBo.getFirstName() != null) ? userBo.getFirstName() : "Soccrates User") + " "
										+ ((userBo.getLastName() != null) ? userBo.getLastName() : ""));
						mailContent = mailContent.replace("#{loginId}", userEntity.getLoginId());
						mailContent = mailContent.replace("#{password}", "Welcome@123");
						mailContent = mailContent.replace("#{admin}", "Soccrates Admin");

					}
					userEntity.setUserType(ApplicationConstants.USERTYPE_COACH);
				}
				session.save(userEntity);
				session.getTransaction().commit();
				userBo.setUserId(userEntity.getUserId());
				try {
					if(mailContent!=null&&mailContent.trim().length()>0)
					SendMail.sendExceptionMail("Thank you for Registering on Soccrates", mailContent,
							userBo.getEmailId(), UtilitySession.getSession());
					;
				} catch (Exception e) {
					userBo.setMessage("User created successfully Mail not set");
				}
			} else {
				throw new SoccratesException(SoccratesExceptionCode.COMPANYIDNOTSET);
			}

		} catch (ParseException e) {

			e.printStackTrace();
			throw new SoccratesException(e.getMessage(), SoccratesExceptionCode.DATEFORMATEXCEPTION);
		} catch (org.hibernate.exception.ConstraintViolationException e) {
			throw new SoccratesException(
					"User Name : " + userBo.getLoginId() + " already exist, please try with other LoginId",
					SoccratesExceptionCode.USERNOTFOUND);

		} catch (HibernateException exception) {
			exception.printStackTrace();
			throw new SoccratesException(exception.getMessage(), SoccratesExceptionCode.HIBERNATEEXCEPION);

		} catch (Exception e) {

			e.printStackTrace();
			throw new SoccratesException(e.getMessage(), SoccratesExceptionCode.UNHANDLEDEXCEPION);
		} finally {
			logger.exiting(this.getClass().getName(), "createUser()");
			if (session.isOpen()) {
				session.close();
			}
		}
		return userBo;

	}

	public UserBO updateUser(UserBO userBO) throws SoccratesException {
		Session session = UtilitySession.getSession();
		session.beginTransaction();
		try {
			logger.entering(this.getClass().getName(), "updateUser()", userBO);
			UserEntity userEntity = null;
			if (userBO.getLoginId() == null || userBO.getLoginId().trim().equalsIgnoreCase("")) {
				userEntity = (UserEntity) session.get(UserEntity.class, userBO.getUserId());
				userBO.setLoginId(userEntity.getLoginId());
			} else {
				String queryString = "from UserEntity where loginId=:loginId";
				Query query = session.createQuery(queryString);
				query.setString("loginId", userBO.getLoginId());
				query.setMaxResults(1);
				userEntity = (UserEntity) query.uniqueResult();
			}
			if (userEntity != null) {
				// userEntity.setCreationDate(new Date());
				userEntity.setUpdationDate(new Date());
				userEntity.setEmailId(userBO.getEmailId());
				userEntity.setFirstName(userBO.getFirstName());
				userEntity.setEmailId(userBO.getEmailId());
				userEntity.setLastName(userBO.getLastName());

				userEntity.setPlayerNumber(userBO.getPlayerNumber());
				userEntity.setPosition(userBO.getPosition());
				userEntity.setFavPlayer(userBO.getFavPlayer());
				
				// if (userBO.getLoginId() != null) {
				// userEntity.setLoginId(userBO.getLoginId());
				// }
				if (userBO.getPassword() != null) {
					userEntity.setPassword(AESencrp.encrypt(userBO.getPassword()));
				}
				if (userBO.getPhoneNumber() != null && userBO.getPhoneNumber().trim().length() > 0)
					userEntity.setPhoneNumber(userBO.getPhoneNumber());
				userEntity.setDateOfBirth((userBO.getDateOfBirth() != null)
						? ApplicationConstants.US_DATE_FORMAT.parse(userBO.getDateOfBirth())
						: null);

				session.update(userEntity);
				session.getTransaction().commit();

			} else {
				throw new SoccratesException(SoccratesExceptionCode.USERNOTFOUND);
			}

			// }
		} catch (ParseException e) {

			e.printStackTrace();
			throw new SoccratesException(e.getMessage(), SoccratesExceptionCode.DATEFORMATEXCEPTION);
		} catch (org.hibernate.exception.ConstraintViolationException e) {
			e.printStackTrace();
			throw new SoccratesException(
					" User Name : " + userBO.getLoginId() + " already exist, please try with other name",
					SoccratesExceptionCode.USERALREADYPRESENT);

		} catch (HibernateException exception) {
			exception.printStackTrace();
			throw new SoccratesException(exception.getMessage(), SoccratesExceptionCode.HIBERNATEEXCEPION);

		} catch (Exception e) {

			e.printStackTrace();
			throw new SoccratesException(e.getMessage(), SoccratesExceptionCode.UNHANDLEDEXCEPION);
		} finally {
			logger.exiting(this.getClass().getName(), "UpdateEmployee()");
			if (session.isOpen()) {
				session.close();
			}
		}
		return userBO;

	}

	public UserBO getUserById(UserBO userBO) throws SoccratesException {
		logger.entering(this.getClass().getName(), "getEmployeeById()", new Object[] { userBO });
		Session session = (Session) UtilitySession.getSession();
		session.beginTransaction();
		// UserBO employeeBO = new UserBO();
		try {
			UserEntity userEntity = (UserEntity) session.get(UserEntity.class, userBO.getUserId());

			if (userEntity != null) {
				SettingsEntity settingsEntity = (SettingsEntity) session.get(SettingsEntity.class,
						userEntity.getCompanyId().getSettingsId().getSettingsId());

				userBO.copy(userEntity, settingsEntity.getUrlPath());
			} else {
				throw new SoccratesException(SoccratesExceptionCode.USERNOTFOUND);
			}

		} catch (HibernateException exception) {
			exception.printStackTrace();
			logger.log(Level.SEVERE, "Exceptionxception", exception);
			throw new SoccratesException(SoccratesExceptionCode.HIBERNATEEXCEPION);

		} finally {
			logger.exiting(this.getClass().getName(), "getEmployeeById()", userBO);
			if (session.isOpen()) {
				session.close();
			}
		}
		return userBO;

	}

	// @SuppressWarnings("unchecked")
	// public UserList searchUser(Session session, UserSearchBO userSearchBO) throws
	// SoccratesException {
	// logger.entering(this.getClass().getName(), "searchUser()", new Object[] {
	// userSearchBO, session });
	// UserList userList = new UserList();
	//
	// UserListBO userListBO = new UserListBO();
	// try {
	// //
	// if(checkAuthorizationOfEmployee(employeeId,Integer.parseInt(ApplicationConstants.EMPLOYEE_MODULE+ApplicationConstants.VIEW)))
	// // {
	//
	// boolean start = false;
	// // String from= " " ;
	// if (userSearchBO.getRetailerId() > 0) {
	// userSearchBO.setUserType(ApplicationConstants.EMPLOYEE);
	// } else {
	// userSearchBO.setUserType(ApplicationConstants.SUPER_ADMIN_EMPLOYEE);
	// }
	// String from = " select
	// u.userId,u.firstName,u.lastName,u.phoneNumber,u.mobileNumber,u.emailId,u.loginId,
	// t.roleName, u.retailerId.companyName , u.retailerId.businessName ";
	// String listString = " from UserEntity u JOIN u.roles t ";
	// String countString = " select count (*) ", queryString = "";
	// if (userSearchBO.getRetailerId() > 0) {
	// if (start) {
	// queryString = queryString + " and ";
	// } else {
	// queryString = queryString + " where ";
	// start = true;
	// }
	// queryString = queryString + " u.retailerId = :retailerId and u.userId
	// !=:userId ";
	// } else {
	// if (start) {
	// queryString = queryString + " and ";
	// } else {
	// queryString = queryString + " where ";
	// start = true;
	// }
	// queryString = queryString + " t.roleType IN (2,3,4,5,6,7,8,9,10) ";
	// }
	// if (userSearchBO.getCompanyName() != null) {
	// if (start) {
	// queryString = queryString + " and ";
	// } else {
	// queryString = queryString + " where ";
	// start = true;
	// }
	// queryString = queryString + " u.retailerId.companyName like :companyName ";
	// }
	// if (userSearchBO.getEmailId() != null) {
	// if (start) {
	// queryString = queryString + " and ";
	// } else {
	// queryString = queryString + " where ";
	// start = true;
	// }
	// queryString = queryString + " u.emailId like :emailId ";
	// }
	// if (userSearchBO.getName() != null) {
	// if (start) {
	// queryString = queryString + " and ";
	// } else {
	// queryString = queryString + " where ";
	// start = true;
	// }
	// queryString = queryString + " ( firstName like :firstName or lastName like
	// :firstName ) ";
	// }
	// // Filters New Query
	// if (userSearchBO.getLastName() != null) {
	// if (start) {
	// queryString = queryString + " and ";
	// } else {
	// queryString = queryString + " where ";
	// start = true;
	// }
	// queryString = queryString + " lastName like :lastName ";
	// }
	// if (userSearchBO.getLoginId() != null) {
	// if (start) {
	// queryString = queryString + " and ";
	// } else {
	// queryString = queryString + " where ";
	// start = true;
	// }
	// queryString = queryString + " loginId like :loginId ";
	// }
	// if (userSearchBO.getRoleName() != null) {
	// if (start) {
	// queryString = queryString + " and ";
	// } else {
	// queryString = queryString + " where ";
	// start = true;
	// }
	// queryString = queryString + " t.roleName like :roleName ";
	// }
	//
	// if (userSearchBO.getPhoneNumber() != null) {
	// if (start) {
	// queryString = queryString + " and ";
	// } else {
	// queryString = queryString + " where ";
	// start = true;
	// }
	// queryString = queryString + " phoneNumber like :phoneNumber ";
	// }
	// if (userSearchBO.getMobileNumber() != null) {
	// if (start) {
	// queryString = queryString + " and ";
	// } else {
	// queryString = queryString + " where ";
	// start = true;
	// }
	// queryString = queryString + " mobileNumber like :mobileNumber ";
	// }
	// if (userSearchBO.getActive() > 0) {
	// if (start) {
	// queryString = queryString + " and ";
	// } else {
	// queryString = queryString + " where ";
	// start = true;
	// }
	// queryString = queryString + " active = :active ";
	// }
	//
	// countString = countString + listString + queryString;
	// // Sort Logic
	// if (userSearchBO.getSortColumn() != null &&
	// !userSearchBO.getSortColumn().equals("")) {
	//
	// queryString = queryString + " order by " + userSearchBO.getSortColumn();
	// if (userSearchBO.getSortOrder() == 1) {
	// queryString = queryString + " ASC";
	// } else {
	// queryString = queryString + " DESC";
	// }
	// listString = from + listString + queryString;
	// } else {
	// listString = from + listString + queryString + " order by u.userId DESC";
	// }
	// org.hibernate.Query query = session.createQuery(listString);
	// org.hibernate.Query countQuery = session.createQuery(countString);
	// System.out.println(listString + "\n" + countString);
	//
	// if (userSearchBO.getRetailerId() > 0) {
	// query.setLong("retailerId", userSearchBO.getRetailerId());
	// query.setLong("userId", userSearchBO.getId());
	//
	// countQuery.setLong("retailerId", userSearchBO.getRetailerId());
	// countQuery.setLong("userId", userSearchBO.getId());
	//
	// }
	// if (userSearchBO.getEmailId() != null) {
	// query.setString("emailId", userSearchBO.getEmailId() + "%");
	// countQuery.setString("emailId", userSearchBO.getEmailId().trim() + "%");
	//
	// }
	// if (userSearchBO.getName() != null) {
	// query.setString("firstName", userSearchBO.getName() + "%");
	// countQuery.setString("firstName", userSearchBO.getName() + "%");
	//
	// }
	// // Filter Params new
	// if (userSearchBO.getLastName() != null) {
	// query.setString("lastName", userSearchBO.getLastName() + "%");
	// countQuery.setString("lastName", userSearchBO.getLastName() + "%");
	//
	// }
	// if (userSearchBO.getRoleName() != null) {
	// query.setString("roleName", userSearchBO.getRoleName() + "%");
	// countQuery.setString("roleName", userSearchBO.getRoleName() + "%");
	//
	// }
	// if (userSearchBO.getLoginId() != null) {
	// query.setString("loginId", userSearchBO.getLoginId().trim() + "%");
	// countQuery.setString("loginId", userSearchBO.getLoginId().trim() + "%");
	//
	// }
	// // Filter Params Ends here
	// if (userSearchBO.getPhoneNumber() != null) {
	// query.setString("phoneNumber", userSearchBO.getPhoneNumber().trim() + "%");
	// countQuery.setString("phoneNumber", userSearchBO.getPhoneNumber().trim() +
	// "%");
	//
	// }
	// if (userSearchBO.getCompanyName() != null) {
	// query.setString("companyName", userSearchBO.getCompanyName().trim() + "%");
	// countQuery.setString("companyName", userSearchBO.getCompanyName().trim() +
	// "%");
	//
	// }
	// if (userSearchBO.getMobileNumber() != null) {
	// query.setString("imeiNumber", userSearchBO.getMobileNumber().trim() + "%");
	// countQuery.setString("imeiNumber", userSearchBO.getMobileNumber().trim() +
	// "%");
	//
	// }
	// if (userSearchBO.getActive() > 0) {
	// boolean active = false;
	// if (userSearchBO.getActive() == 1) {
	// active = true;
	// }
	// query.setBoolean("active", active);
	// countQuery.setBoolean("active", active);
	//
	// }
	//
	// int pageSize = 0;
	// /*
	// * SettingEntityHandler settings = new SettingEntityHandler();
	// * settings.getPageSize(userSearchBO.getCallType()); if
	// * (userSearchBO.getPageNumber() > 0) { pageSize =
	// * settings.getPageSize(userSearchBO.getCallType());
	// * userList.setPageSize(pageSize);
	// * userList.setPageNumber(userSearchBO.getPageNumber());
	// * query.setMaxResults(pageSize); query.setFirstResult(pageSize *
	// * (userSearchBO.getPageNumber() - 1)); }
	// */
	// // Pagination based on Record Number for new UI
	// if (userSearchBO.getRecordNumber() >= 0 && userSearchBO.getPageSize() > 0) {
	// query.setMaxResults(userSearchBO.getPageSize());
	// query.setFirstResult(userSearchBO.getRecordNumber());
	// // Enable caching so pagination is faster
	// // Not sure if this works
	// query.setCacheable(true);
	// query.setCacheMode(CacheMode.NORMAL);
	//
	// }
	// ArrayList<UserListBO> boList = new ArrayList<UserListBO>();
	// // List<UserEntity>u=query.list();
	// // System.out.println(u.size());
	// List<Object[]> o = query.list();
	// // System.out.println(o.size()+" ,"+o+","+o.get(0));
	// for (Object[] oo : o) {
	//
	// Object[] w = oo;
	// System.out.println(w[0] + "," + w[1] + "," + w.length);
	// UserListBO u = new UserListBO();
	// Long l = (Long) oo[0];
	// u.setUserId(l.longValue());
	// u.setFirstName((String) w[1]);
	// u.setLastName((String) oo[2]);
	// u.setPhoneNumber((String) oo[3]);
	// u.setMobileNumber((String) oo[4]);
	// u.setEmailId((String) oo[5]);
	// u.setLoginId((String) oo[6]);
	//
	// u.setRoleName((String) oo[7]);
	// u.setCompanyName((String) oo[8]);
	// String businessName = (String) oo[9];
	// StringBuffer orgName = new StringBuffer();
	// orgName.append((u.getCompanyName() != null) ? u.getCompanyName() : "-NA-");
	// orgName.append(" (");
	// orgName.append((businessName != null) ? businessName : "-NA");
	// orgName.append(")");
	// u.setOrgName(orgName.toString());
	// // System.out.println("oo"+oo[6]);
	// // Set<RoleEntity>roles=((Set<RoleEntity>) oo[6]);
	// // System.out.println("roles"+roles);
	// boList.add(u);
	// // System.out.println(o.get(1));
	// }
	// // List<EmployeeEntity> eList=
	// // for (EmployeeEntity employeeEntity : eList) {
	// // EmployeeBO eBO= new EmployeeBO();
	// // eBO.copy(employeeEntity);
	// // boList.add(eBO);
	// // }
	// userList.setUsers(boList);
	// userList.setPageNumber(userSearchBO.getPageNumber());
	// userList.setListSize(boList.size());
	// // To get total Records
	// Long totalRecord = (Long) countQuery.uniqueResult();
	// userList.setTotalRecords(totalRecord);
	// if (userSearchBO.getPageNumber() > 0) {
	// Long totalrecords = (Long) countQuery.uniqueResult();
	// logger.info("Total Employee=" + totalrecords);
	// userList.setTotalRecords(totalrecords.intValue());
	// if (totalrecords > 0 & pageSize > 0) {
	// int pages = 0;
	// Float f = ((float) totalrecords / pageSize);
	// pages = f.intValue();
	//
	// if ((f - pages) > 0) {
	// pages++;
	//
	// }
	// userList.setTotalPages(pages);
	// }
	//
	// }
	//
	// } catch (HibernateException exception) {
	//
	// exception.printStackTrace();
	//
	// logger.log(Level.SEVERE, "Exception", exception);
	// throw new TaxometryException(exception.getMessage());
	//
	// } finally {
	// logger.exiting(this.getClass().getName(), "searchEmployee()", userListBO);
	//
	// }
	// return userList;
	// }
	//

	public boolean deleteUser(UserBO userBO) throws SoccratesException {
		// long userId = userBO.getUserId();
		boolean delete = false;
		Session session = UtilitySession.getSession();
		session.beginTransaction();
		try {

			if (logger.isLoggable(Level.FINER)) {

				logger.entering(this.getClass().getName(), "deleteUser()", userBO);
			}
			UserEntity r = null;
			if (userBO.getLoginId() == null || userBO.getLoginId().trim().equalsIgnoreCase("")) {
				r = (UserEntity) session.get(UserEntity.class, userBO.getUserId());
			} else {
				String queryString = "from UserEntity where loginId=:loginId";
				Query query = session.createQuery(queryString);
				query.setString("loginId", userBO.getLoginId());
				query.setMaxResults(1);
				r = (UserEntity) query.uniqueResult();
			}
			if (r != null) {
				r.setActive(false);
			} else {
				throw new SoccratesException(SoccratesExceptionCode.USERNOTFOUND);
			}
			session.getTransaction().commit();
			delete = true;
		} catch (HibernateException exception) {
			logger.log(Level.SEVERE, "Exceptionn", exception);
			exception.printStackTrace();
			throw new SoccratesException(exception.getMessage(), SoccratesExceptionCode.HIBERNATEEXCEPION);

		} finally {
			logger.exiting(this.getClass().getName(), "deleteUser()");
			if (session.isOpen()) {
				session.close();
			}
		}
		return delete;
	}

	// public boolean changePassword(Session session, ChangePasswordBO
	// changePasswordBO) throws SoccratesException {
	//
	// boolean changed = false;
	// try {
	//
	// logger.entering(this.getClass().getName(), "changePassword()", new Object[] {
	// session, changePasswordBO });
	//
	// if (changePasswordBO.getNewPassword() != null &&
	// (!"".equals(changePasswordBO.getNewPassword()))) {
	// UserEntity userEntity = (UserEntity) session.get(UserEntity.class,
	// changePasswordBO.getUserId());
	// if (userEntity != null) {
	// String password1;
	// try {
	// password1 = AESencrp.decrypt(userEntity.getPassword());
	// } catch (Exception e1) {
	// throw new TaxometryException(e1.getMessage());
	// }
	// if (password1.equals(changePasswordBO.getOldPassword())) {
	// String password2;
	//
	// try {
	// password2 = AESencrp.encrypt(changePasswordBO.getNewPassword());
	// } catch (Exception e) {
	//
	// e.printStackTrace();
	// throw new TaxometryException(e.getMessage());
	// }
	// userEntity.setPassword(password2);
	//
	// session.update(userEntity);
	//
	// changed = true;
	// } else {
	// throw new
	// TaxometryException(ApplicationErrorMessage.PASSWORD_DOES_NOT_MATCH);
	// }
	// } else {
	// throw new UserNotFoundException(ApplicationErrorMessage.USER_NOT_FOUND);
	// }
	// } else {
	// throw new TaxometryException("Password cant be null!!");
	// }
	//
	// } catch (TaxometryException e1) {
	//
	// logger.log(Level.SEVERE, "Uncaught exception", e1);
	// throw e1;
	// } catch (HibernateException e) {
	//
	// e.printStackTrace();
	// logger.log(Level.SEVERE, "Uncaught exception", e);
	// throw new TaxometryException(e.getMessage());
	// } finally {
	// logger.exiting(this.getClass().getName(), "changePassword()", changed);
	//
	// }
	// return changed;
	// }

	public long checkUniqueEmailId(String emailId) throws SoccratesException {
		logger.entering(this.getClass().getName(), "checkUniqueEmailId()", new Object[] { emailId });

		long id = 0;
		Session session = UtilitySession.getSession();
		session.beginTransaction();
		try {
			if (emailId == null) {
				throw new SoccratesException(SoccratesExceptionCode.EMAILIDEMPTY);
			}
			Query query = null;

			query = session.createQuery("select userId from UserEntity where trim(emailId) like :emailId ");
			query.setString("emailId", emailId.trim());

			query.setMaxResults(1);
			Object obj = query.uniqueResult();
			if (obj != null) {
				id = (Long) obj;
			}

		} catch (HibernateException exception) {
			logger.log(Level.SEVERE, "Uncaught exception", exception);
			exception.printStackTrace();
			throw new SoccratesException(exception.getMessage(), SoccratesExceptionCode.HIBERNATEEXCEPION);

		} finally {
			logger.exiting(this.getClass().getName(), "checkUniqueEmailId()", id);
			if (session.isOpen()) {
				session.close();
			}
		}
		return id;
	}

	// public ChangePasswordBO resetPassword(Session session, ChangePasswordBO
	// emailId) throws SoccratesException {
	// // TODO Auto-generated method stub
	//
	// try {
	// Query query = session.createQuery(" from UserEntity where emailId like
	// :emailId ");
	// query.setMaxResults(1);
	// query.setString("emailId", emailId.getLoginId().trim());
	// UserEntity userEntity = (UserEntity) query.uniqueResult();
	// if (userEntity != null) {
	// // userEntity.setChangePasswordRequest(true);
	// String autoGeneratedCode = "autoGeneratedCode";
	// if (userEntity.getPassword() != null && userEntity.getPassword().length() >
	// 0)
	// autoGeneratedCode = userEntity.getPassword().substring(0, 10);
	//
	// if (autoGeneratedCode.equalsIgnoreCase(emailId.getToken().trim())) {
	// if (emailId.getNewPassword() != null) {
	// userEntity.setPassword(AESencrp.encrypt(emailId.getNewPassword()));
	// session.save(userEntity);
	// emailId.setMessage("Password Changed!!");
	// } else {
	// emailId.setIsException(true);
	// emailId.setMessage("Please provide password");
	//
	// }
	// } else {
	// emailId.setIsException(true);
	// emailId.setMessage("Kindly Contact Admin. User not requested for Reset
	// Password");
	// }
	//
	// } else {
	// throw new TaxometryException("Email-Id is not valid");
	// }
	// } catch (
	//
	// HibernateException exception) {
	//
	// logger.log(Level.SEVERE, "Uncaught exception", exception);
	// exception.printStackTrace();
	// session.getTransaction().rollback();
	// emailId.setMessage(exception.getLocalizedMessage());
	// emailId.setIsException(true);
	//
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	//
	// e.printStackTrace();
	// emailId.setMessage(e.getLocalizedMessage());
	// emailId.setIsException(true);
	//
	// } finally {
	// logger.exiting(this.getClass().getName(), "sendForgetPasswordChangeLink()");
	//
	// }
	// return emailId;
	// }

}
