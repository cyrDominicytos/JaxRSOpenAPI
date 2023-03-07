package fr.istic.taa.jaxrs.services;

public class OldDataFormator<T> {

		private String message;
		private T oldObject;
		
		public OldDataFormator(T oldObject, String message) {
			super();
			this.message = message;
			this.oldObject = oldObject;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public T getOldObject() {
			return oldObject;
		}
		public void setOldObject(T oldObject) {
			this.oldObject = oldObject;
		}
		
}
