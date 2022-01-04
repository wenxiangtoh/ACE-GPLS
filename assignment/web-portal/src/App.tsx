import './App.css';
import {BrowserRouter, Route, Switch} from 'react-router-dom';
import Feedback from "./pages/Feedback";
import FeedbackInfo from "./pages/FeedbackInfo";
import Home from "./pages/Home";
import {QueryClient, QueryClientProvider} from "react-query";

const queryClient = new QueryClient();

function App() {

  return (
      <div className="App">
        <QueryClientProvider client={queryClient}>
          <BrowserRouter>
            <Switch>
              <Route exact path='/'>
                <Home/>
              </Route>
              <Route path='/feedbacks/view-feedback-status'>

                <FeedbackInfo/>

              </Route>
              <Route path='/feedbacks'>
                <Feedback/>
              </Route>
            </Switch>
          </BrowserRouter>
        </QueryClientProvider>
      </div>
  )
      ;
}

export default App;
