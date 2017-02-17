package rapid.container;

import com.kodcu.rapid.path.Container;
import com.kodcu.rapid.provider.JsonProvider;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Assert;
import org.junit.Test;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by hakan on 16/02/2017.
 */
public class TestContainerList extends ContainerConfig {

    @Test
    public void shouldListRunningContainers() {
        final Response response = target("containers").path("json").request(MediaType.APPLICATION_JSON).get();
        assertEquals(200, response.getStatus());
        final JsonArray responseContent = response.readEntity(JsonArray.class);
        int expected = 1;
        assertEquals(expected, responseContent.size());
        response.close();
    }

    @Test
    public void shouldListAllContainers() {
        final Response response = target("containers").path("json").queryParam("all", true).request(MediaType.APPLICATION_JSON).get();
        assertEquals(200, response.getStatus());
        final JsonArray responseContent = response.readEntity(JsonArray.class);
        int expected = 1;
        assertThat(expected, is(1));
        response.close();
    }

    @Test
    public void shouldListContainersWithSize() {
        final Response response = target("containers").path("json").queryParam("size", true).request(MediaType.APPLICATION_JSON).get();
        assertEquals(200, response.getStatus());
        final JsonArray responseContent = response.readEntity(JsonArray.class);
        int expected = 1;
        assertEquals(expected, responseContent.size());
        JsonObject obj = (JsonObject) responseContent.get(0);
        assertThat(obj.containsKey("SizeRw"), is(true));
        response.close();
    }
}
