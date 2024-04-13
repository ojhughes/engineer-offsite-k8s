/*
 * Copyright 2018 Google LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package example.resources;

import com.codahale.metrics.annotation.Timed;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import example.JibExampleConfiguration;
import example.api.Saying;
import example.config.HelloWorldConfiguration;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource {

  private final String template;
  private final String defaultName;
  private final AtomicLong counter;

  public HelloWorldResource(String template, String defaultName) {
    this.template = template;
    this.defaultName = defaultName;
    this.counter = new AtomicLong();
  }

  public static HelloWorldResource from(JibExampleConfiguration conf) {
    final HelloWorldConfiguration helloConfiguration = conf.getHelloConfiguration();
    return new HelloWorldResource(
        helloConfiguration.getTemplate(), helloConfiguration.getDefaultName());
  }

  @GET
  @Timed
  public Saying sayHello(@QueryParam("name") Optional<String> name) {
    final String value = String.format(template, name.orElse(defaultName));
    return new Saying(counter.incrementAndGet(), value);
  }
}
