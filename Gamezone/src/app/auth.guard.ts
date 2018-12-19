import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot,Router } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor( private router: Router) {}
  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    let url: string = state.url;
    console.log("in gaurd: "+url)
    return this.checkLogin(url);
  }
  checkLogin(url: string): boolean {
    if (localStorage.getItem('ID') || localStorage.getItem('admin')) { return true; }
    // Navigate to the login page with extras
    this.router.navigate(['/signin']);
    return false;
  }
}
