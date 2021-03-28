import {Injectable} from '@angular/core';

const TOKEN_KEY = "TOKEN"
const USER_KEY = "USER"

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {

  constructor() {
  }

  public save(token: Token): void {
    window.sessionStorage.removeItem(TOKEN_KEY)
    window.sessionStorage.setItem(TOKEN_KEY, JSON.stringify(token))
  }

  public getToken(): Token | null {
    const token = window.sessionStorage.getItem(TOKEN_KEY);
    if (token) {
      return JSON.parse(token)
    }
    return null
  }

  public saveUser(user: User): void {
    window.sessionStorage.removeItem(USER_KEY)
    window.sessionStorage.setItem(USER_KEY, JSON.stringify(user))
  }

  public getUser(): any | null {
    const user = window.sessionStorage.getItem(USER_KEY)
    if (user) {
      return JSON.parse(user)
    }
    return null
  }

  clear(): void {
    window.sessionStorage.clear();
  }
}
