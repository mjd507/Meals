package io.mjd507;

import com.sun.mail.util.MailSSLSocketFactory;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by mjd on 2018/6/9 21:41
 */
public class EmailUtil {

  private static Logger logger = LoggerFactory.getLogger(EmailUtil.class);

  public static void sendEmail(String fromEmail, String mailAuthToken,
      String toEmail, String subject, String body) {
    try {
      Session session = getQQSession(fromEmail, mailAuthToken);
      MimeMessage msg = new MimeMessage(session);
      //set message headers
      msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
      msg.addHeader("format", "flowed");
      msg.addHeader("Content-Transfer-Encoding", "8bit");

      msg.setFrom(new InternetAddress(fromEmail, MimeUtility.encodeText("订餐系统", "UTF-8", "B")));

      msg.setReplyTo(InternetAddress.parse(fromEmail, false));

      msg.setSubject(subject, "UTF-8");

      msg.setText(body, "UTF-8");

      msg.setSentDate(new Date());

      msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
      Transport.send(msg);
      logger.info("邮件发送成功，收件人：{}", toEmail);
    } catch (Exception e) {
      e.printStackTrace();
      logger.error("邮件发送失败，收件人：{}，邮件主题：{}，邮件内容：{}", toEmail, subject, body);
    }
  }

  private static Session getQQSession(String fromEmail, String authToken)
      throws GeneralSecurityException {
    String hostServer = "smtp.qq.com";  //QQ 邮件服务器
    Properties props = System.getProperties();
    props.put("mail.smtp.host", hostServer);
    props.put("mail.smtp.auth", "true");
    MailSSLSocketFactory sf = new MailSSLSocketFactory();
    sf.setTrustAllHosts(true);
    props.put("mail.smtp.ssl.enable", "true");
    props.put("mail.smtp.ssl.socketFactory", sf);

    return Session.getDefaultInstance(props, new Authenticator() {
      public PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(fromEmail, authToken);
      }
    });
  }
}