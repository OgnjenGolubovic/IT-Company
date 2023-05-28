export interface SoftwareEngineer {
    id: number;
    name: String;
    surname: String;
}

export interface Project {
    pr_id: number;
    name: String;
}

export interface RegisterResponse {
    status: number;
    message: string;
  }