export interface RegisterRequest {
    email: string;
    name: string;
    surname: string;
    street: string;
    streetNumber: number;
    city: string;
    state: string;
    phone: string;
    companyRole: string;
    title: string;
    password: string;
    IsActivatedAcount: boolean;
  }
  
  /*
  Interface for the Register Response (can look different, based on your backend api)
  */
  export interface RegisterResponse {
    status: number;
    message: string;
  }

  export interface CompanyRole {
    value: string;
    viewValue: string;
  }