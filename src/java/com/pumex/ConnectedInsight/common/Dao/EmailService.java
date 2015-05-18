/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pumex.ConnectedInsight.common.Dao;

import java.io.InputStream;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author user
 */
public class EmailService
{
    Properties mailproperties=new Properties();
    
    public void sendingMail(String[] recipientMailIds, String mailText) 
    {
        try 
        {
            InputStream is=Thread.currentThread().getContextClassLoader().getResourceAsStream("/mail.properties");
            mailproperties.load(is);
            Session Session_mail = Session.getDefaultInstance(mailproperties,new javax.mail.Authenticator()
            {
                @Override
                protected PasswordAuthentication getPasswordAuthentication()
                {
                    return new PasswordAuthentication(mailproperties.getProperty("mail.smtp.user"), mailproperties.getProperty("mail.smtp.password"));
                }
            });
            Message Message_Mail = new MimeMessage(Session_mail);
            Message_Mail.setRecipients(Message.RecipientType.TO, getToAddress(recipientMailIds));
            Message_Mail.setSubject(mailproperties.getProperty("mail.subject"));
            Message_Mail.setSentDate(new Date());
            Message_Mail.setText(mailText);
            Transport.send(Message_Mail, Message_Mail.getAllRecipients());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public InternetAddress[] getToAddress(String[] toList)
    {
        InternetAddress[] internetAddresses = new InternetAddress[toList.length];
        try
        {
            for (Integer i = 0; i < toList.length; i++)
            {
                internetAddresses[i] = new InternetAddress(toList[i]);
            }
        }
        catch (Exception ex)
        {
        }
        return internetAddresses;
    }
}
