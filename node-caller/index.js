const AWS = require('aws-sdk');
const lambda = new AWS.Lambda({region: 'us-west-1'});

exports.handler = async (event) => {
    // TODO implement
    // const response = {
    //     statusCode: 200,
    //     body: JSON.stringify('Hello from Lambda!'),
    // };
    //JSON.stringify(event, null, 2)
    
    console.log('Lambda Received event:', event);
    //context.succeed('Hello ' + event.name);
    //return response;
    return callPayment(event.id);
};

const callPayment = async (id)=>{
    console.log(`STARTING callPayment(${id})`)
    return await new Promise((resolve,reject)=>{
        const params = {
            FunctionName: 'java-test-lambda-function-17MGIDOKVQDWC',
            Payload: JSON.stringify({id})
        };
        lambda.invoke(params,(err,results)=>{
           if(err){ 
               console.debug("ERROR from invoking AWS",err);
               reject(err);
           }
           if(results){ 
               console.debug("SUCCESS!! from invoking AWS",results);
               resolve(results);
           }
           
        });
    });
};
