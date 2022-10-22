/*package br.com.residencia.ecommerce.service;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;


@Service
public class EmailService {
	@Autowired
	public JavaMailSender emailSender;
	
	@Value("${mail.from}")
	private String mailFrom;
	
	@Value("${spring.mail.host}")
	private String	emailServerHost;	
	
	@Value("${spring.mail.port}")
	private Integer emailServerPort;
	
	@Value("${spring.mail.username}")
			private String emailServerUserName;	
	
	@Value("${spring.mail.password}")
	private String emailServerPassword;

	public JavaMailSender javaMailSender() {
		JavaMailSenderImpl  mailSender = new JavaMailSenderImpl();
		Properties prop = new Properties();
		
		mailSender.setHost(emailServerHost);
		mailSender.setPort(emailServerPort);
		mailSender.setUsername(emailServerUserName);
		mailSender.setPassword(emailServerPassword);
		prop.put("mail.smtp.auth", true);
		prop.put("mail.smtp.atrattls.enable", true);
		mailSender.setJavaMailProperties(prop);
		
		return mailSender;
		
	}  
	public EmailService(JavaMailSender javaMailSender) {
		this.emailSender = javaMailSender;
		
	}
	public void sendEmail(String destinatario,String assunto, String mensagem) {
	public void sendHtmlEmail(String toEmail,String subject, String message) throws Exception{
		this.emailSender = javaMailSender();
		var mailMessage = new SimpleMailMessage();
		
		try {
			helper.setFrom(mailFrom);
			helper.setTo(toEmail);
			helper.setSubject(subject);
			
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("<html>\r\n");
			sBuilder.append("	<body>\r\n");
			sBuilder.append("		<div align=\"center\">\r\n");
			sBuilder.append("			E-MAIL NO FORMATO HTML\\r\\n");
			sBuilder.append("		</div>\r\n");
			sBuilder.append("		<BR/>\r\n");
			sBuilder.append("		<center>\r\n");
			sBuilder.append(message);
			sBuilder.append("		<center>\r\n");
			sBuilder.append("	</body>\r\n");
			sBuilder.append("</html>\r\n");
			
			helper.setText(sBuilder.toString(),true);
			
			emailSender.send(mimeMessage);
			
		} catch(Exception e) {
			throw new Exception("Erro ao enviar o email." + e.getMessage());
		}
		
		
		MimeMessage mimeMessage = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
		
		mailMessage.setTo(destinatario);
		mailMessage.setSubject(assunto);
		mailMessage.setText(mensagem);
		mailMessage.setFrom(mailFrom);
		try {
		emailSender.send(mailMessage);
		}catch(Exception e) {
			System.out.println("Ocorreu um erro e não foi possível enviar o email."+ e);
		}
	}
}*/
