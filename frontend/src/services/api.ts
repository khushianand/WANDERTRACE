import axios from 'axios';
export const api=axios.create({baseURL:'/api',withCredentials:true});
export type ApiResult<T>={success:boolean;data:T;message:string|null};
