package docker.web.console.bean;

import java.io.Serializable;

public class Response<T> implements Serializable {

	private static final long serialVersionUID = 2037600920765220243L;

	private boolean success;
	private T data;

	public Response() {
		super();
	}

	public Response(boolean success, T data) {
		super();
		this.success = success;
		this.data = data;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}