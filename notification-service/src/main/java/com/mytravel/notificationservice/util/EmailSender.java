package com.mytravel.notificationservice.util;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.*;
import java.security.GeneralSecurityException;
import java.util.Properties;

public final class EmailSender {

    /**
     * Assume my personal qq email account is the official account of MyTravel website.
     * @param to
     * @param subject
     * @param content
     */
    public static void sendEmail(String to, String subject, String content) throws GeneralSecurityException {
        String host = "smtp.qq.com";
        String username="2078492396@qq.com";
        String from="2078492396@qq.com";
        String password="xgdlybkuvbsfhhfd"; // qq邮箱的授权密码，交作业前记得删掉
        // 设置邮件服务器属性
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.port", "465"); // SMTP端口号
        properties.setProperty("mail.smtp.auth", "true");
        // properties.setProperty("mail.smtp.starttls.enable", "true"); // 启用TLS
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.ssl.socketFactory", sf);

        // 创建会话
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password); // 填入SMTP认证的用户名和密码
            }
        });

        try {
            // 创建默认的MimeMessage对象
            MimeMessage message = new MimeMessage(session);

            // Set From: 设置发件人
            message.setFrom(new InternetAddress(from));

            // Set To: 设置收件人
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: 设置邮件主题
            message.setSubject(subject);

            // 设置消息体
            message.setText(content);

            // 发送消息
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

//    public static void main(String[] args) throws GeneralSecurityException {
//        String to = "francislgr@outlook.com";   // 收件人电子邮箱
//        String subject = "Hello from Java Mail"; // 邮件主题
//        String content = "This is a simple test email from Java program."; // 邮件内容
//
//        sendEmail(to, subject, content);
//    }
}
