
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;

import entity.Customer;
import proxy.ApiGatewayProxyResponse;
import proxy.ApiGatewayRequest;
import service.AgentService;

public class GetCustomerById implements RequestHandler<ApiGatewayRequest, ApiGatewayProxyResponse> {

	private AgentService agentService = new AgentService();

	public ApiGatewayProxyResponse handleRequest(ApiGatewayRequest request, Context context) {
		LambdaLogger logger = context.getLogger();
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Access-Control-Allow-Origin", "*");
		headers.put("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization");
		try {
			if (request.getPathParameters() == null || request.getPathParameters().get("customerId") == null) {
				return new ApiGatewayProxyResponse(400, headers, null);
			}
			Customer customer = agentService
					.getCustomerById(Long.parseLong(request.getPathParameters().get("customerId")));
			if (customer == null) {
				return new ApiGatewayProxyResponse(404, headers, null);
			}
			return new ApiGatewayProxyResponse(200, headers, new Gson().toJson(customer));
		} catch (NumberFormatException | SQLException e) {
			logger.log(e.getMessage());
			return new ApiGatewayProxyResponse(400, headers, e.getMessage());
		} catch (ClassNotFoundException e) {
			logger.log(e.getMessage());
			return new ApiGatewayProxyResponse(500, headers, null);
		}
	}
}