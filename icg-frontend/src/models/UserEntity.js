export default class User {
    constructor(username, password, email, role, token, id) {
        this.username = username;
        this.password= password;
        this.email = email;
        this.role = role;
        this.token = token;
        this.id = id;
    }
}