export {};

declare global {
    export interface ContactInfo {
        name: string,
    }
    
    export interface Contact extends ContactInfo {
        id: number,
    }
}