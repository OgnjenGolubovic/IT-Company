import { AbstractControl, ValidationErrors } from "@angular/forms";

export class CustomValidators {
  static passwordsMatching(control: AbstractControl): ValidationErrors | null {
    const password = control.get('password')?.value;
    const passwordConfirm = control.get('passwordConfirm')?.value;

    // Check if passwords are matching. If not then add the error 'passwordsNotMatching: true' to the form
    if ((password === passwordConfirm) && (password !== null && passwordConfirm !== null)) {
      if (password.length > 7 && /[A-Z]/.test(password)) {
        return null; // Password is valid
      } else {
        return { invalidPassword: true }; // Password is invalid
      }
    } else {
      return { passwordsNotMatching: true };
    }
  }

}