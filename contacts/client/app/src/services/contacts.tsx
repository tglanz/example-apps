export interface Contact {
    id: number,
    first: string,
    last: string,
    avatar?: string,
}

export async function getContacts(): Promise<Contact[]> {
    return [{
        id: 1,
        first: "A1",
        last: "A2",
        avatar: "https://www.gravatar.com/avatar/8f38e71a3538e8f8f4912bc1b4526518",
    }, {
        id: 2,
        first: "B1",
        last: "B2",
        avatar: "https://www.gravatar.com/avatar/8f38e71a3538e8f8f4912bc1b4526518",
    }];
}