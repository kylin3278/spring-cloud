package provider;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceInstanceRestController {

	private static final Logger logger = LoggerFactory.getLogger(ServiceInstanceRestController.class);

	@Autowired
	private DiscoveryClient discoveryClient; // 服务发现客户端

	@GetMapping(value = "/add")
	public Integer add(@RequestParam Integer a, @RequestParam Integer b) {
		Integer r = a - b;
		String applicationName = "";
		List<ServiceInstance> instances = discoveryClient.getInstances(applicationName);
		for (ServiceInstance instance : instances) {
			logger.info("/add, host:" + instance.getHost() + ", service_id:" + instance.getServiceId() + ", result:" + r);
		}
		//ServiceInstance instance = discoveryClient.getLocalServiceInstance();
		//logger.info("/add, host:" + instance.getHost() + ", service_id:" + instance.getServiceId() + ", result:" + r);
		return r;
	}

}
