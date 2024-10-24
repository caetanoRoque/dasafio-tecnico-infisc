
import React, { useState } from "react";
import axios from "axios";
import './App.css';
import FileUpload from "./componentes/FileUpload.js";

function App() {
  const [responseData, setResponseData] = useState('');
  const [view, setView] = useState('');


  const fetchData = async (url) => {
    try {
      const response = await axios.get(url);
      setResponseData(response.data); 
    } catch (error) {
      setResponseData(`Erro na requisição: ${error.message}`);
    }
  };


  const handleClickA = () => {
    setView('A');
    fetchData('http://localhost:8080/quantidadeFuncionarios'); 
  };

  const handleClickB = () => {
    setView('B');
    fetchData('http://localhost:8080/quantidadeClientes'); 
  };

  const handleClickC = () => {
    setView('C');
    fetchData('http://localhost:8080/quantidadeVendas'); 
  };

  const handleClickD = () => {
    setView('D');
    fetchData('http://localhost:8080/totalVendas'); 
  };

  const handleClickE = () => {
    setView('E');
    fetchData('http://localhost:8080/totalVendasPorMes'); 
  };
  
  const handleClickG = () => {
    setView('G');
    fetchData('http://localhost:8080/aniversariantesDoMes');
  };

  const handleClickH = () => {
    setView('H');
    fetchData('http://localhost:8080/totalComprasPorCliente'); 
  };

  const handleClickI = () => {
    setView('I');
    fetchData('http://localhost:8080/totalVendasPorFuncionario'); 
  };
  const handleClickJ = () => {
    setView('J');
    fetchData('http://localhost:8080/clienteQueMaisComprou'); 
  };

  const handleClickK = () => {
    setView('K');
    fetchData('http://localhost:8080/funcionariosMaisTempoTrabalho'); 
  };

  return (
    <div className="App">
      <FileUpload />
      <div>
        <button onClick={handleClickA}>A</button>
        <button onClick={handleClickB}>B</button>
        <button onClick={handleClickC}>C</button>
        <button onClick={handleClickD}>D</button>
        <button onClick={handleClickE}>E</button>
        <button onClick={handleClickG}>G</button>
        <button onClick={handleClickH}>H</button>
        <button onClick={handleClickI}>I</button>
        <button onClick={handleClickJ}>J</button>
        <button onClick={handleClickK}>K</button>
      </div>

      {view === 'A' && <div>Resposta A: {responseData}</div>}
      {view === 'B' && <div>Resposta B: {responseData}</div>}
      {view === 'C' && <div>Resposta C: {responseData}</div>}
      {view === 'D' && <div>Resposta D: R${responseData}</div>}
      {view === 'E' && (
        <div>
          <h2>Resposta E:</h2>
          {Array.isArray(responseData) ? ( 
            responseData.length > 0 ? (
              <ul>
                {responseData.map((item, index) => (
                  <li key={index}>
                    <h3>{item.mes}</h3>
                    <p>{`R$ ${item.totalVendas}`}</p>
                  </li>
                ))}
              </ul>
            ) : (
              <p>Sem dados disponíveis</p>
            )
          ) : (
            <p>Formato de dados inesperado: {JSON.stringify(responseData)}</p> 
          )}
        </div>
      )}

      {view === 'G' && (
        <div>
          <h2>Resposta G:</h2>
          {Array.isArray(responseData) ? (
            responseData.length > 0 ? (
              <ul>
                {responseData.map((item, index) => (
                  <li key={index}>
                    <p>{`Nome: ${item.nome || 'Não disponível'}`}</p>
                    <p>{`Mês: ${item.mes || 'Não disponível'}`}</p>
                    <p>{`Dia: ${item.diaAniversario || 'Não disponível'}`}</p>
                    <p>{`Idade: ${item.idade || 'Não disponível'}`}</p>
                  </li>
                ))}
              </ul>
            ) : (
              <p>Não há ninguém de aniversário neste mês.</p>
            )
          ) : (
            <p>Formato de dados inesperado: {JSON.stringify(responseData)}</p>
          )}
        </div>
      )}

{view === 'H' && (
        <div>
          <h2>Resposta H:</h2>
          {Array.isArray(responseData) ? (
            responseData.length > 0 ? (
              <ul>
                {responseData.map((item, index) => (
                  <li key={index}>
                    <p>{`Nome: ${item.nome || 'Não disponível'}`}</p>
                    <p>{`Total Gasto: R$${item.totalGasto || 'Não disponível'}`}</p>
                  </li>
                ))}
              </ul>
            ) : (
              <p>Sem dados disponíveis</p>
            )
          ) : (
            <p>Formato de dados inesperado: {JSON.stringify(responseData)}</p>
          )}
        </div>
      )}
      {view === 'I' && (
        <div>
          <h2>Resposta I:</h2>
          {Array.isArray(responseData) ? (
            responseData.length > 0 ? (
              <ul>
                {responseData.map((item, index) => (
                  <li key={index}>
                    <p>{`Nome: ${item.nome || 'Não disponível'}`}</p>
                    <p>{`Total Vendas: R$${item.totalVendas || 'Não disponível'}`}</p>
                  </li>
                ))}
              </ul>
            ) : (
              <p>Sem dados disponíveis</p>
            )
          ) : (
            <p>Formato de dados inesperado: {JSON.stringify(responseData)}</p>
          )}
        </div>
      )}
      {view === 'J' && <div>Resposta J: {`Cliente que mais comprou foi: ${responseData}`}</div>}
      {view === 'K' && (
        <div>
          <h2>Resposta K:</h2>
          {Array.isArray(responseData) ? (
            responseData.length > 0 ? (
              <ul>
                {responseData.map((item, index) => (
                  <li key={index}>
                    <p>{`Nome: ${item.nome || 'Não disponível'}`}</p>
                    <p>{`Data Contratação: ${item.dataContratacao || 'Não disponível'}`}</p>
                  </li>
                ))}
              </ul>
            ) : (
              <p>Sem dados disponíveis</p>
            )
          ) : (
            <p>Formato de dados inesperado: {JSON.stringify(responseData)}</p>
          )}
        </div>
      )}
    </div>
  );
}

export default App;
