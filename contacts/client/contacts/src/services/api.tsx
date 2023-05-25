import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";

const data = [{
    id: 1,
    name: "A",
}, {
    id: 2,
    name: "B",
}];

export async function getContacts(): Promise<Contact[]> {
    return data;
}

export async function createContact(contact: ContactInfo) {
    const maxId = Math.max(...data.map(curr => curr.id));
    data.push({
        ...contact,
        id: maxId + 1,
    });
}

export const useContacts = () => useQuery({
    refetchInterval: 1,
    queryKey: ["get-contacts"],
    queryFn: getContacts,  
});

export const useCreateContact = () => {
    const queryClient = useQueryClient();
    return useMutation({
        mutationKey: ["create-contact"],
        mutationFn: createContact,
        onSuccess: () => {
            queryClient.invalidateQueries();
        }
    })
}