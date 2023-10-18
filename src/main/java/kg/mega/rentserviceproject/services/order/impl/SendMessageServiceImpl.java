package kg.mega.rentserviceproject.services.order.impl;

import kg.mega.rentserviceproject.models.order.Order;
import kg.mega.rentserviceproject.services.order.SendMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.Properties;

@Service
public class SendMessageServiceImpl implements SendMessageService {
    @Value("${spring.mail.host}")
    private String mailHost;

    @Value("${spring.mail.port}")
    private int mailPort;

    @Value("${spring.mail.username}")
    private String mailUsername;

    @Value("${spring.mail.password}")
    private String mailPassword;

    @Override
    public void sendOrderToEmail(Order order) {
        final String
            userEmail = order.getUser().getEmail(),
            title = "Rent car service order",
            bodyMessagePath = "src/main/java/kg/mega/rentserviceproject/files/bodyMessage.txt";

        String
            username       = order.getUser().getUsername(),
            carName        = order.getCar().getModel().getProducer().getName().toUpperCase()
                           + " " + order.getCar().getModel().getName().toUpperCase(),
            modelClass     = order.getCar().getModel().getModelClass().toString(),
            bodyType       = order.getCar().getModel().getBodyType().toString(),
            yearProduction = order.getCar().getYearProduction().toString(),
            vin            = order.getCar().getVin(),
            startDate      = order.getFromDate().toString(),
            endDate        = order.getToDate().toString();

        Integer countDays = order.getCountDays();
        BigDecimal totalAmount = order.getTotalAmount();
        String totalAmountFormat = String.format("%.2f", totalAmount);

        try {
            Properties properties = System.getProperties();
            properties.setProperty("mail.host", mailHost);
            properties.setProperty("mail.port", String.valueOf(mailPort));
            properties.setProperty("mail.username", mailUsername);
            properties.setProperty("mail.password", mailPassword);
            properties.setProperty("mail.smtp.auth", "true");
            properties.setProperty("mail.smtp.starttls.enable", "true");
            Session session = Session.getInstance(properties, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("${spring.mail.username}", "${spring.mail.password}");
                }
            });

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("bolotbekove32@gmail.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(userEmail));
            message.setSubject(title);

            BodyPart textPart;

            try (BufferedReader reader = new BufferedReader(new FileReader(bodyMessagePath))) {
                StringBuilder textContent = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    textContent.append(line).append("\n");
                }

                String formattedTextContent = String.format(
                        textContent.toString(),
                        username,
                        carName,
                        yearProduction,
                        modelClass,
                        bodyType,
                        vin,
                        startDate,
                        endDate,
                        countDays,
                        totalAmountFormat
                );

                textPart = new MimeBodyPart();
                textPart.setText(formattedTextContent);
            }

            BodyPart imagePart = new MimeBodyPart();
            DataSource source = new FileDataSource(order.getCar().getPathImg());
            imagePart.setDataHandler(new DataHandler(source));
            imagePart.setFileName("image.jpg");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(textPart);
            multipart.addBodyPart(imagePart);

            message.setContent(multipart);

            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}