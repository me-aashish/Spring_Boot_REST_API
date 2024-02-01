package com.aashish.springboot.firstspringbootrestapi.helloworld;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorlController {

	@RequestMapping("/hello")
	public String HelloWorld() {
		return "Hello World";
	}

	@RequestMapping("/hello-bean")
	public HelloWorldBean HelloWorldBean() {
		return new HelloWorldBean("Hello from bean");
	}

	@RequestMapping("/hello-path-param/{name}")
	public HelloWorldBean HelloWorldBean(@PathVariable String name) {
		return new HelloWorldBean("Hello from bean " + name);
	}

}
