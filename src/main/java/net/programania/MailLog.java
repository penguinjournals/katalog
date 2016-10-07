package net.programania;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;	

public class MailLog 
{   
	private final Properties properties = new Properties();
	private Session session;
	List<String> logBuffer = new ArrayList<String>();
	
    public void addLine( String line )
    {
    	logBuffer.add(line);
    }
	public void persist() {
		String body = new String();
		for (int line = 0; line < logBuffer.size(); line++) {
			body = body+(logBuffer.get(line) + "\n");
		}
		System.out.println(body);
		sendEmail(body);
	}
	
 
	private void setupEmail() {
 
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.port",25);
		properties.put("mail.smtp.mail.sender","******");
		properties.put("mail.smtp.user", "******");
		properties.put("mail.smtp.password", "******");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.to", "******");
		properties.put("mail.smtp.subject", "Kata Log last");
 
		session = Session.getDefaultInstance(properties);
	}
 
	public void sendEmail(String body){
 
		setupEmail();
		try{
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress((String)properties.get("mail.smtp.mail.sender")));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress((String)properties.get("mail.smtp.to")));
			message.setSubject((String)properties.get("mail.smtp.subject"));
			message.setText(body);
			Transport t = session.getTransport("smtp");
			t.connect((String)properties.get("mail.smtp.user"), (String)properties.get("mail.smtp.password"));
			t.sendMessage(message, message.getAllRecipients());
			t.close();
		}catch (MessagingException e){
			System.out.println("Couldn't send email");
			e.printStackTrace();
		}
		
	}
}
