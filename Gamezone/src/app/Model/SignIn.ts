export class SignIn {
	email: string;
	id: string;
	image: string;
	name: string;
	provider: string;
	token: string;
	city: string;
	state: string;
	country: string;
	phone: string;
	secondPage: string;
	blocked:string

	constructor(email, id, image, name, provider, token) {
		this.email = email
		this.name = name
		this.provider = provider
		this.id = id
		this.image = image
		this.token = token
	}
}