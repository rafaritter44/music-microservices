package com.github.ilegra.final_project.app_service.ribbon;

import static java.util.stream.Collectors.toList;

import java.util.List;

import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.LoadBalancerBuilder;
import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.reactive.LoadBalancerCommand;
import com.netflix.loadbalancer.reactive.ServerOperation;

import rx.Observable;

public class LoadBalancer {
	
	private BaseLoadBalancer loadBalancer;
	
	public LoadBalancer(List<String> ipsAndPorts) {
		loadBalancer = LoadBalancerBuilder.newBuilder()
				.buildFixedServerListLoadBalancer(getServers(ipsAndPorts));
	}
	
	public String getURL() {
		return LoadBalancerCommand
				.<String>builder()
				.withLoadBalancer(loadBalancer)
				.build()
				.submit(new ServerOperation<String>() {
					@Override
					public Observable<String> call(Server server) {
						try {
							return Observable.just(server.getHostPort());
						} catch (Exception e) {
							return Observable.error(e);
						}
					}
				}).toBlocking().first();
	}
	
	private List<Server> getServers(List<String> ipsAndPorts) {
		return ipsAndPorts.stream()
				.map(ipAndPort -> ipAndPort.split(":"))
				.map(this::toServer)
				.collect(toList());
	}
	
	private Server toServer(String[] ipAndPort) {
		return new Server(ipAndPort[0], Integer.parseInt(ipAndPort[1]));
	}
	
}
