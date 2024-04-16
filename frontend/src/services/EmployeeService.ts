import axios from 'axios';

const BASE_URL = "http://localhost:8080/dlut/employee";


export const getEmployeesList = async () => {
    try {
        const response = await axios.get(`${BASE_URL}/showAll`);
        return response.data;
    } catch (error) {
        
        if (axios.isAxiosError(error)) {
            console.error('Axios error:', error.response?.data);
            throw new Error(error.response?.data?.message || 'Unknown error');
        } else {
            console.error('Unexpected error:', error);
            throw new Error('An unexpected error occurred');
        }
    }
};
