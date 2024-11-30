package com.api.v1.payment.domain;

import com.api.v1.cards.domain.Card;
import com.api.v1.medical_appointments.domain.MedicalAppointment;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Document
public record Payment(
        @BsonId
        ObjectId id,
        ObjectId number,
        Card card,
        MedicalAppointment medicalAppointment,
        BigDecimal price,
        String createdAt,
        ZoneId createdAtZone
) {

    public static Payment create(Card card, MedicalAppointment medicalAppointment, double price) {
        return new Payment(
                new ObjectId(),
                new ObjectId(),
                card,
                medicalAppointment,
                new BigDecimal(price),
                LocalDateTime.now().toString(),
                ZoneId.systemDefault()
        );
    }

}
