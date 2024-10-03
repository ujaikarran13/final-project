# Week 6 Review: National Park Service API

In this exercise, you have the opportunity to use two public APIs. One is from the National Park Service (NPS) that provides data and content about their parks, campgrounds, visitor centers, tours, and more. The other gives you a set of challenges that you must use the National Park Service API to answer, as well as giving you an endpoint to submit your answers to see if you're correct.

The goal of this exercise is to give you more experience using Postman and reading API documentation. There is no starting code for this exercise.

## Getting started

Before using the National Park Service API, you need to sign up for an **API key**. An API key is a unique identifier used to authenticate a user or application with an API. The NPS provides API keys free of charge—go to https://www.nps.gov/subjects/developer/get-started.htm and fill the form to receive your key. It's best if each student in your group fills out the form and receives their own API key.

> Note: it's best practice is to keep API keys secret. While it's not the end of the world if someone gets your NPS API key, other APIs may provide sensitive information or charge you for each request. The worst thing that can happen with the NPS API key getting out is that you can't make requests for a period of time.

You need to provide the API key with every request. The NPS API can accept the key in three different ways as shown in their documentation: https://www.nps.gov/subjects/developer/guides.htm. The [Postman](#postman) section later in this README walks you through on setting up the API key in an HTTP header that's sent with every request.

## Exercise API

You can find documentation for the API at [https://teapi.netlify.app/docs/national-park-exercise/docs](https://teapi.netlify.app/docs/national-park-exercise/docs).

You'll use the *Get Challenges* endpoints to get the challenge questions you'll need to solve. Then use the *Verify Challenge* endpoint to check your answer.

You must use the answer format provided in the *Get Challenge* JSON response in the request body of the *Verify Challenge* `POST` request. If your answer is correct, you'll get a `200` response. Any other status is incorrect and includes an explanation in the response body.

### Example

The `/challenges/1` endpoint returns data similar to the following:

```json
{
    "id": "1", // the challenge number, you need this ID to submit your answer
    "question": "How many parks does the National Parks API provide data on?", // the question you need to answer
    "hint": "Use /parks to retrieve data about national parks.", // a clue on how to look up the information and the endpoint to use
    "answer": "" // the format of your answer, is either a single empty string or an object with multiple fields, see the next section for more information
}
```

Once you have established the answer by querying the National Park Service API, make a `POST` request to the Exercise API's `/challenges/1/verify` endpoint to `POST` the answer:

```json
{
    "apiKey": "1a2b3c4d5e6f7g8h9i0j1k2l3m4n5o6p7q8r9s0t", // your NPS API key
    "answer": "23" // challenge answer
}
```

As indicated in the API documentation, you must supply both your National Park Service API key and the challenge answer.

Notes:

- The `answer` field is either a single string or an object. The `/challenges` endpoint indicates the format of the answer.
- Make sure to use strings as indicated in the JSON format, even with numeric values.
- For many challenges, you may find it easier to copy the relevant fields and data from the NPS API and paste into your `POST` request to the exercise API.


## National Park Service API

The National Park Service provides online documentation of their API at https://www.nps.gov/subjects/developer/api-documentation.htm. They offer several endpoints that provide different datasets. You're not expected to read the entire contents of the documentation, but it's a good idea to look at the documentation for each endpoint as you use it. The challenge problems tell you which endpoint you need to answer it.

The documentation for each endpoint contains a brief description of what it provides. If you click the blue bar, it expands to show the parameters that you can use to narrow the results by park, state, or those that match a search term.

Following the parameters is an example of the data you can expect from the endpoint. To view the data fields without examples, you can click the small blue link that reads **Model**, or go to the section titled **Models** at bottom of the documentation page. For each endpoint, the NPS provides the format of the data and descriptions of most fields.

## Postman

You'll use Postman in this exercise to make requests to both APIs. Postman offers many features that make interacting with APIs easier. One such feature is a **Collection** which is a way to group similar requests. A collection also allows you to set the same authorization method for all requests in the collection.

To create a new collection, click the **Collections** button on the sidebar in Postman, and then click the **+** button:

![Postman new collection](./img/postman_new_collection.png)

After you create the new collection, give it a meaningful name like _National Park Exercise_:

![Postman name collection](./img/postman_name_collection.png)

On the same screen you can set the authorization method and provide your NPS API token. First, change the **Type** drop-down to **API Key**. A section appears following the drop-down. In the **Key** textbox type "X-Api-Key" (without the quotes) and in the **Value** textbox copy and paste your NPS API key. Leave the **Add to** drop-down on **Header**:

![Postman authorization](./img/postman_authorization.png)

You can now begin adding requests to the collection that uses the same authorization. In the **Collections** view on the side find the new collection you just created. Click the three dots **○○○** and select **Add Request**:

![Postman new request](./img/postman_new_request.png)

Do this for all requests that you send to the National Park Service API and it automatically sends your API key with it. If you get a response from the API that your API key isn't valid, make sure your request is under the collection you created:

![Postman parent collection](./img/postman_parent_collection.png)

The [Using Postman for HTTP GET video](https://lms.techelevator.com/content_link/gitlab.com/te-curriculum/intro-to-tools-lms/postman/03-making-GET-request.md) in the _Intro to Tools: Postman_ unit also gives a demonstration of creating a new collection and making `GET` requests.

> Note: Even though you set the API Key in the Authorization tab, you must still send it in the request body to the Exercise API for validating your work.

### Exporting a collection

You may wish to export a Postman collection to share with your teammates or instructor. Click the same three dots **○○○** next to your collection in the **Collections** view and select **Export**:

![Postman export collection](./img/postman_export_collection.png)

Keep the default recommended format and click the **Export** button. Then select the location where you want to save it.
