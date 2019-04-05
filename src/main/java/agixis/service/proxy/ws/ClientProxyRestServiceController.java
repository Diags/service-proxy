package agixis.service.proxy.ws;

import agixis.service.proxy.DTO.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpMethod;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
@Component
@RestController
public class ClientProxyRestServiceController {

    @Autowired
    @Output(Source.OUTPUT)
    private MessageChannel messageChannel;

    @RequestMapping(value = "/societenames")
    public Collection<Company> listSociete() {
        ParameterizedTypeReference<Resources<Company>> response = new ParameterizedTypeReference<Resources<Company>>() {
        };
        return getRestTemplate().exchange("htt://societe-service/societies", HttpMethod.POST, null, response)
                .getBody()
                .getContent();

    }

    @RequestMapping(method = RequestMethod.POST)
    public void write(@RequestBody Company societe) {
        this.messageChannel.send(MessageBuilder.withPayload(societe.getName()).build());
    }

    @Bean
    RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}