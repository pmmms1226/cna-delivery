package shop;

import shop.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @Autowired
    DeliveryRepository deliveryRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverOrdered_Ship(@Payload Ordered ordered){

        if(ordered.isMe()){
            Delivery delivery = new Delivery();
            delivery.setOrderId(ordered.getId());
            delivery.setStatus("SHIPPED");
            System.out.println(ordered.getId());
            deliveryRepository.save(delivery);
        }
    }

}
