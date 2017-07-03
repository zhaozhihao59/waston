package com.tocersoft.email;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.tocersoft.email.dao.ISysEmailDao;
import com.tocersoft.email.entity.EmailServerConfig;
import com.tocersoft.email.entity.SysEmail;
import com.tocersoft.email.service.MailSenderProvider;
import com.tocersoft.email.service.SysEmailServerConfigService;

@Component
public class MailSendTask {
	
	Logger logger = Logger.getLogger(MailSendTask.class);
	
	@Autowired
	MailSenderProvider senderProvider;
	
	@Autowired
	private ISysEmailDao sysEmailDao;
	
	@Autowired
	private SysEmailServerConfigService configService;

	/**
	 * 发送邮件每10秒执行一次
	
	*/
	@Scheduled(cron = "0/10 * * * * ?")
	public void sendMailTask()throws Exception{
		// 取出未发送邮件列表
		List<SysEmail> sysEmailList = sysEmailDao.listTenNoSendMail();
		if(sysEmailList != null && sysEmailList.size() > 0){
			Properties props = new Properties();
			props.put("mail.debug", "true");
			props.put("mail.smtp.auth", "true");
			Session s = Session.getInstance(props);
			
			MimeMessage message = new MimeMessage(s); 
			
			EmailServerConfig config = configService.getEmailServerConfig();
			
	        InternetAddress from = new InternetAddress(config.getAccount());  
	        message.setFrom(from);
	        
	        for (SysEmail sysEmail : sysEmailList) {
	        	message.setSubject(sysEmail.getTitle());
	        	BodyPart mdp = new MimeBodyPart();  
		        mdp.setContent(sysEmail.getContent(), "text/html;charset=utf-8");  
		        Multipart mm = new MimeMultipart();
		        mm.addBodyPart(mdp); 
		        message.setContent(mm);
				String toEmail = sysEmail.getEmail();
				if(StringUtils.isNotBlank(toEmail)){
					String[] to = toEmail.split(";");
					InternetAddress[] Toaddress = new InternetAddress[to.length];  
			        for (int i = 0; i < to.length; i++)  
			            Toaddress[i] = new InternetAddress(to[i]);  
			        message.setRecipients(Message.RecipientType.TO, Toaddress);  
				}
				message.setSentDate(new Date()); 
				message.saveChanges();
				Transport transport = s.getTransport("smtp");  
		        transport.connect(config.getHostName(), config.getAccount(), config.getPassword());  
		        transport.sendMessage(message, message.getAllRecipients());  
		        transport.close();  
				
				sysEmailDao.updateEmailState(sysEmail.getId(), 1);
			}
		}
	}
}
