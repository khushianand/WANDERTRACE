import axios from 'axios';
export type Media={id:string;type:'IMAGE'|'VIDEO'|'AUDIO';url:string;thumbnailUrl?:string;title?:string;caption?:string};
export type Memory={title:string;subtitle:string;story:string;memoryDate:string;destination:string;country:string;coverImageUrl:string;photos:Media[];videos:Media[];audio:Media[];locations:{name:string;description:string;latitude:number;longitude:number;visitedAt?:string}[];timeline:{title:string;description:string;eventTime:string}[];tags:string[]};
export async function getPublicMemory(token:string){const {data}=await axios.get<{success:boolean;data:Memory}>(`/api/public/memories/${encodeURIComponent(token)}`);return data.data}
