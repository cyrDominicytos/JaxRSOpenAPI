package fr.istic.taa.jaxrs.dto;

public abstract class SupportDtoTemp {
	private String name;
	private String email;
	private String grad = "DEVELOPER";
	
	public SupportDtoTemp(String name, String email, String grad) {
        this.name = name;
        this.email = email;
        this.grad = grad;
    }
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getGrad() {
		return grad;
	}


	public void setGrad(String grad) {
		this.grad = grad;
	}
		
	public static SupportDto create(String type, String name, String email, String grad) {
	    switch (type) {
	        case "SupportCreateDto":
	           return new SupportCreateDto(name, email, grad);
	        case "SupportDefaultDto":
	            throw new IllegalArgumentException("Missing breed and coat color parameters for creating a Dog object");
	        default:
	            throw new IllegalArgumentException("Unknown animal type: " + type);
	    }
	}

	public static SupportDto create(String type, String name, String email, String grad, int id, String created_at) {
	    switch (type) {
	        case "SupportCreateDto":
	        	 return new SupportCreateDto(name, email, grad);
	        case "Cat":
	        	 return new SupportDefaultDto(id, name, email, grad,created_at);
	        default:
	            throw new IllegalArgumentException("Support type: " + type);
	    }

}
	
	
	/**
	 * @author ctossou
	 * SupportCreateDto to match support creation data object
	 */
	class SupportCreateDto extends SupportDto{
		public SupportCreateDto(String name, String email, String grad) {
			super(name, email, grad);
		}
	}
	
	/**
	 * 
	 * @author ctossou
	 * SupportDefaultDto, this class will be used to return a full Support format
	 */
	class SupportDefaultDto extends SupportDto{
		
		private String created_at;
		private int id;
		
		public SupportDefaultDto(int id, String name, String email, String grad, String created_at) {
			super(name, email, grad);
			this.id = id;
			this.created_at = created_at;
		}
		
	}
