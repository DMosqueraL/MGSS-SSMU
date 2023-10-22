export class ApiService{

    async post(data:any, url:string){
        let rta;

        await fetch(`${process.env.NEXT_PUBLIC_BACKEND_SERVER_URL+url}`, 
            {method: 'POST', headers: {"Content-Type": "application/json"}, 
            body: JSON.stringify(data)})
                .then(response => response.json())
                .then(data => rta = data)
                .catch(error => {
                    console.log(error);
                    rta = {message: "Algo salió mal..."};
                });

        return rta;
    }

    async patch(data:any, url:string){
        let rta;
        
        await fetch(`${process.env.NEXT_PUBLIC_BACKEND_SERVER_URL+url}`, 
            {method: 'PATCH', headers: {"Content-Type": "application/json"}, 
            body: JSON.stringify(data)})
                .then(response => response.json())
                .then(data => rta = data)
                .catch(error => {
                    console.log(error);
                    rta = {message: "Algo salió mal..."};
                });

        return rta;
    }

    async get(url:string){
        let rta;
        
        await fetch(`${process.env.NEXT_PUBLIC_BACKEND_SERVER_URL+url}`, 
            {method: 'GET'})
                .then(response => response.json())
                .then(data => rta = data)
                .catch(error => {
                    console.log(error);
                    rta = {message: "Algo salió mal..."};
                });

        return rta;
    }

}