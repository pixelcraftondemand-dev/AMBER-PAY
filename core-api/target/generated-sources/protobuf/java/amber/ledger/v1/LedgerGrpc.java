package amber.ledger.v1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.68.1)",
    comments = "Source: ledger.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class LedgerGrpc {

  private LedgerGrpc() {}

  public static final java.lang.String SERVICE_NAME = "amber.ledger.v1.Ledger";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<amber.ledger.v1.LedgerOuterClass.HoldRequest,
      amber.ledger.v1.LedgerOuterClass.HoldResponse> getHoldFundsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "HoldFunds",
      requestType = amber.ledger.v1.LedgerOuterClass.HoldRequest.class,
      responseType = amber.ledger.v1.LedgerOuterClass.HoldResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<amber.ledger.v1.LedgerOuterClass.HoldRequest,
      amber.ledger.v1.LedgerOuterClass.HoldResponse> getHoldFundsMethod() {
    io.grpc.MethodDescriptor<amber.ledger.v1.LedgerOuterClass.HoldRequest, amber.ledger.v1.LedgerOuterClass.HoldResponse> getHoldFundsMethod;
    if ((getHoldFundsMethod = LedgerGrpc.getHoldFundsMethod) == null) {
      synchronized (LedgerGrpc.class) {
        if ((getHoldFundsMethod = LedgerGrpc.getHoldFundsMethod) == null) {
          LedgerGrpc.getHoldFundsMethod = getHoldFundsMethod =
              io.grpc.MethodDescriptor.<amber.ledger.v1.LedgerOuterClass.HoldRequest, amber.ledger.v1.LedgerOuterClass.HoldResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "HoldFunds"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  amber.ledger.v1.LedgerOuterClass.HoldRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  amber.ledger.v1.LedgerOuterClass.HoldResponse.getDefaultInstance()))
              .setSchemaDescriptor(new LedgerMethodDescriptorSupplier("HoldFunds"))
              .build();
        }
      }
    }
    return getHoldFundsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<amber.ledger.v1.LedgerOuterClass.CaptureRequest,
      amber.ledger.v1.LedgerOuterClass.JournalResponse> getCaptureHoldMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CaptureHold",
      requestType = amber.ledger.v1.LedgerOuterClass.CaptureRequest.class,
      responseType = amber.ledger.v1.LedgerOuterClass.JournalResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<amber.ledger.v1.LedgerOuterClass.CaptureRequest,
      amber.ledger.v1.LedgerOuterClass.JournalResponse> getCaptureHoldMethod() {
    io.grpc.MethodDescriptor<amber.ledger.v1.LedgerOuterClass.CaptureRequest, amber.ledger.v1.LedgerOuterClass.JournalResponse> getCaptureHoldMethod;
    if ((getCaptureHoldMethod = LedgerGrpc.getCaptureHoldMethod) == null) {
      synchronized (LedgerGrpc.class) {
        if ((getCaptureHoldMethod = LedgerGrpc.getCaptureHoldMethod) == null) {
          LedgerGrpc.getCaptureHoldMethod = getCaptureHoldMethod =
              io.grpc.MethodDescriptor.<amber.ledger.v1.LedgerOuterClass.CaptureRequest, amber.ledger.v1.LedgerOuterClass.JournalResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CaptureHold"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  amber.ledger.v1.LedgerOuterClass.CaptureRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  amber.ledger.v1.LedgerOuterClass.JournalResponse.getDefaultInstance()))
              .setSchemaDescriptor(new LedgerMethodDescriptorSupplier("CaptureHold"))
              .build();
        }
      }
    }
    return getCaptureHoldMethod;
  }

  private static volatile io.grpc.MethodDescriptor<amber.ledger.v1.LedgerOuterClass.ReleaseRequest,
      amber.ledger.v1.LedgerOuterClass.JournalResponse> getReleaseHoldMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ReleaseHold",
      requestType = amber.ledger.v1.LedgerOuterClass.ReleaseRequest.class,
      responseType = amber.ledger.v1.LedgerOuterClass.JournalResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<amber.ledger.v1.LedgerOuterClass.ReleaseRequest,
      amber.ledger.v1.LedgerOuterClass.JournalResponse> getReleaseHoldMethod() {
    io.grpc.MethodDescriptor<amber.ledger.v1.LedgerOuterClass.ReleaseRequest, amber.ledger.v1.LedgerOuterClass.JournalResponse> getReleaseHoldMethod;
    if ((getReleaseHoldMethod = LedgerGrpc.getReleaseHoldMethod) == null) {
      synchronized (LedgerGrpc.class) {
        if ((getReleaseHoldMethod = LedgerGrpc.getReleaseHoldMethod) == null) {
          LedgerGrpc.getReleaseHoldMethod = getReleaseHoldMethod =
              io.grpc.MethodDescriptor.<amber.ledger.v1.LedgerOuterClass.ReleaseRequest, amber.ledger.v1.LedgerOuterClass.JournalResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ReleaseHold"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  amber.ledger.v1.LedgerOuterClass.ReleaseRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  amber.ledger.v1.LedgerOuterClass.JournalResponse.getDefaultInstance()))
              .setSchemaDescriptor(new LedgerMethodDescriptorSupplier("ReleaseHold"))
              .build();
        }
      }
    }
    return getReleaseHoldMethod;
  }

  private static volatile io.grpc.MethodDescriptor<amber.ledger.v1.LedgerOuterClass.TransferRequest,
      amber.ledger.v1.LedgerOuterClass.TransferResponse> getPostTransferMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PostTransfer",
      requestType = amber.ledger.v1.LedgerOuterClass.TransferRequest.class,
      responseType = amber.ledger.v1.LedgerOuterClass.TransferResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<amber.ledger.v1.LedgerOuterClass.TransferRequest,
      amber.ledger.v1.LedgerOuterClass.TransferResponse> getPostTransferMethod() {
    io.grpc.MethodDescriptor<amber.ledger.v1.LedgerOuterClass.TransferRequest, amber.ledger.v1.LedgerOuterClass.TransferResponse> getPostTransferMethod;
    if ((getPostTransferMethod = LedgerGrpc.getPostTransferMethod) == null) {
      synchronized (LedgerGrpc.class) {
        if ((getPostTransferMethod = LedgerGrpc.getPostTransferMethod) == null) {
          LedgerGrpc.getPostTransferMethod = getPostTransferMethod =
              io.grpc.MethodDescriptor.<amber.ledger.v1.LedgerOuterClass.TransferRequest, amber.ledger.v1.LedgerOuterClass.TransferResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PostTransfer"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  amber.ledger.v1.LedgerOuterClass.TransferRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  amber.ledger.v1.LedgerOuterClass.TransferResponse.getDefaultInstance()))
              .setSchemaDescriptor(new LedgerMethodDescriptorSupplier("PostTransfer"))
              .build();
        }
      }
    }
    return getPostTransferMethod;
  }

  private static volatile io.grpc.MethodDescriptor<amber.ledger.v1.LedgerOuterClass.FeeRequest,
      amber.ledger.v1.LedgerOuterClass.JournalResponse> getPostFeeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PostFee",
      requestType = amber.ledger.v1.LedgerOuterClass.FeeRequest.class,
      responseType = amber.ledger.v1.LedgerOuterClass.JournalResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<amber.ledger.v1.LedgerOuterClass.FeeRequest,
      amber.ledger.v1.LedgerOuterClass.JournalResponse> getPostFeeMethod() {
    io.grpc.MethodDescriptor<amber.ledger.v1.LedgerOuterClass.FeeRequest, amber.ledger.v1.LedgerOuterClass.JournalResponse> getPostFeeMethod;
    if ((getPostFeeMethod = LedgerGrpc.getPostFeeMethod) == null) {
      synchronized (LedgerGrpc.class) {
        if ((getPostFeeMethod = LedgerGrpc.getPostFeeMethod) == null) {
          LedgerGrpc.getPostFeeMethod = getPostFeeMethod =
              io.grpc.MethodDescriptor.<amber.ledger.v1.LedgerOuterClass.FeeRequest, amber.ledger.v1.LedgerOuterClass.JournalResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PostFee"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  amber.ledger.v1.LedgerOuterClass.FeeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  amber.ledger.v1.LedgerOuterClass.JournalResponse.getDefaultInstance()))
              .setSchemaDescriptor(new LedgerMethodDescriptorSupplier("PostFee"))
              .build();
        }
      }
    }
    return getPostFeeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<amber.ledger.v1.LedgerOuterClass.ReverseRequest,
      amber.ledger.v1.LedgerOuterClass.JournalResponse> getReverseJournalMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ReverseJournal",
      requestType = amber.ledger.v1.LedgerOuterClass.ReverseRequest.class,
      responseType = amber.ledger.v1.LedgerOuterClass.JournalResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<amber.ledger.v1.LedgerOuterClass.ReverseRequest,
      amber.ledger.v1.LedgerOuterClass.JournalResponse> getReverseJournalMethod() {
    io.grpc.MethodDescriptor<amber.ledger.v1.LedgerOuterClass.ReverseRequest, amber.ledger.v1.LedgerOuterClass.JournalResponse> getReverseJournalMethod;
    if ((getReverseJournalMethod = LedgerGrpc.getReverseJournalMethod) == null) {
      synchronized (LedgerGrpc.class) {
        if ((getReverseJournalMethod = LedgerGrpc.getReverseJournalMethod) == null) {
          LedgerGrpc.getReverseJournalMethod = getReverseJournalMethod =
              io.grpc.MethodDescriptor.<amber.ledger.v1.LedgerOuterClass.ReverseRequest, amber.ledger.v1.LedgerOuterClass.JournalResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ReverseJournal"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  amber.ledger.v1.LedgerOuterClass.ReverseRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  amber.ledger.v1.LedgerOuterClass.JournalResponse.getDefaultInstance()))
              .setSchemaDescriptor(new LedgerMethodDescriptorSupplier("ReverseJournal"))
              .build();
        }
      }
    }
    return getReverseJournalMethod;
  }

  private static volatile io.grpc.MethodDescriptor<amber.ledger.v1.LedgerOuterClass.RefundRequest,
      amber.ledger.v1.LedgerOuterClass.JournalResponse> getRefundPaymentMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RefundPayment",
      requestType = amber.ledger.v1.LedgerOuterClass.RefundRequest.class,
      responseType = amber.ledger.v1.LedgerOuterClass.JournalResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<amber.ledger.v1.LedgerOuterClass.RefundRequest,
      amber.ledger.v1.LedgerOuterClass.JournalResponse> getRefundPaymentMethod() {
    io.grpc.MethodDescriptor<amber.ledger.v1.LedgerOuterClass.RefundRequest, amber.ledger.v1.LedgerOuterClass.JournalResponse> getRefundPaymentMethod;
    if ((getRefundPaymentMethod = LedgerGrpc.getRefundPaymentMethod) == null) {
      synchronized (LedgerGrpc.class) {
        if ((getRefundPaymentMethod = LedgerGrpc.getRefundPaymentMethod) == null) {
          LedgerGrpc.getRefundPaymentMethod = getRefundPaymentMethod =
              io.grpc.MethodDescriptor.<amber.ledger.v1.LedgerOuterClass.RefundRequest, amber.ledger.v1.LedgerOuterClass.JournalResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RefundPayment"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  amber.ledger.v1.LedgerOuterClass.RefundRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  amber.ledger.v1.LedgerOuterClass.JournalResponse.getDefaultInstance()))
              .setSchemaDescriptor(new LedgerMethodDescriptorSupplier("RefundPayment"))
              .build();
        }
      }
    }
    return getRefundPaymentMethod;
  }

  private static volatile io.grpc.MethodDescriptor<amber.ledger.v1.LedgerOuterClass.CreateAccountRequest,
      amber.ledger.v1.LedgerOuterClass.CreateAccountResponse> getCreateAccountMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateAccount",
      requestType = amber.ledger.v1.LedgerOuterClass.CreateAccountRequest.class,
      responseType = amber.ledger.v1.LedgerOuterClass.CreateAccountResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<amber.ledger.v1.LedgerOuterClass.CreateAccountRequest,
      amber.ledger.v1.LedgerOuterClass.CreateAccountResponse> getCreateAccountMethod() {
    io.grpc.MethodDescriptor<amber.ledger.v1.LedgerOuterClass.CreateAccountRequest, amber.ledger.v1.LedgerOuterClass.CreateAccountResponse> getCreateAccountMethod;
    if ((getCreateAccountMethod = LedgerGrpc.getCreateAccountMethod) == null) {
      synchronized (LedgerGrpc.class) {
        if ((getCreateAccountMethod = LedgerGrpc.getCreateAccountMethod) == null) {
          LedgerGrpc.getCreateAccountMethod = getCreateAccountMethod =
              io.grpc.MethodDescriptor.<amber.ledger.v1.LedgerOuterClass.CreateAccountRequest, amber.ledger.v1.LedgerOuterClass.CreateAccountResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateAccount"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  amber.ledger.v1.LedgerOuterClass.CreateAccountRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  amber.ledger.v1.LedgerOuterClass.CreateAccountResponse.getDefaultInstance()))
              .setSchemaDescriptor(new LedgerMethodDescriptorSupplier("CreateAccount"))
              .build();
        }
      }
    }
    return getCreateAccountMethod;
  }

  private static volatile io.grpc.MethodDescriptor<amber.ledger.v1.LedgerOuterClass.AccountRequest,
      amber.ledger.v1.LedgerOuterClass.AccountResponse> getGetAccountMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetAccount",
      requestType = amber.ledger.v1.LedgerOuterClass.AccountRequest.class,
      responseType = amber.ledger.v1.LedgerOuterClass.AccountResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<amber.ledger.v1.LedgerOuterClass.AccountRequest,
      amber.ledger.v1.LedgerOuterClass.AccountResponse> getGetAccountMethod() {
    io.grpc.MethodDescriptor<amber.ledger.v1.LedgerOuterClass.AccountRequest, amber.ledger.v1.LedgerOuterClass.AccountResponse> getGetAccountMethod;
    if ((getGetAccountMethod = LedgerGrpc.getGetAccountMethod) == null) {
      synchronized (LedgerGrpc.class) {
        if ((getGetAccountMethod = LedgerGrpc.getGetAccountMethod) == null) {
          LedgerGrpc.getGetAccountMethod = getGetAccountMethod =
              io.grpc.MethodDescriptor.<amber.ledger.v1.LedgerOuterClass.AccountRequest, amber.ledger.v1.LedgerOuterClass.AccountResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetAccount"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  amber.ledger.v1.LedgerOuterClass.AccountRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  amber.ledger.v1.LedgerOuterClass.AccountResponse.getDefaultInstance()))
              .setSchemaDescriptor(new LedgerMethodDescriptorSupplier("GetAccount"))
              .build();
        }
      }
    }
    return getGetAccountMethod;
  }

  private static volatile io.grpc.MethodDescriptor<amber.ledger.v1.LedgerOuterClass.ListRequest,
      amber.ledger.v1.LedgerOuterClass.ListResponse> getListEntriesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListEntries",
      requestType = amber.ledger.v1.LedgerOuterClass.ListRequest.class,
      responseType = amber.ledger.v1.LedgerOuterClass.ListResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<amber.ledger.v1.LedgerOuterClass.ListRequest,
      amber.ledger.v1.LedgerOuterClass.ListResponse> getListEntriesMethod() {
    io.grpc.MethodDescriptor<amber.ledger.v1.LedgerOuterClass.ListRequest, amber.ledger.v1.LedgerOuterClass.ListResponse> getListEntriesMethod;
    if ((getListEntriesMethod = LedgerGrpc.getListEntriesMethod) == null) {
      synchronized (LedgerGrpc.class) {
        if ((getListEntriesMethod = LedgerGrpc.getListEntriesMethod) == null) {
          LedgerGrpc.getListEntriesMethod = getListEntriesMethod =
              io.grpc.MethodDescriptor.<amber.ledger.v1.LedgerOuterClass.ListRequest, amber.ledger.v1.LedgerOuterClass.ListResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ListEntries"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  amber.ledger.v1.LedgerOuterClass.ListRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  amber.ledger.v1.LedgerOuterClass.ListResponse.getDefaultInstance()))
              .setSchemaDescriptor(new LedgerMethodDescriptorSupplier("ListEntries"))
              .build();
        }
      }
    }
    return getListEntriesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<amber.ledger.v1.LedgerOuterClass.AuditRequest,
      amber.ledger.v1.LedgerOuterClass.AuditResponse> getAuditAccountsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AuditAccounts",
      requestType = amber.ledger.v1.LedgerOuterClass.AuditRequest.class,
      responseType = amber.ledger.v1.LedgerOuterClass.AuditResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<amber.ledger.v1.LedgerOuterClass.AuditRequest,
      amber.ledger.v1.LedgerOuterClass.AuditResponse> getAuditAccountsMethod() {
    io.grpc.MethodDescriptor<amber.ledger.v1.LedgerOuterClass.AuditRequest, amber.ledger.v1.LedgerOuterClass.AuditResponse> getAuditAccountsMethod;
    if ((getAuditAccountsMethod = LedgerGrpc.getAuditAccountsMethod) == null) {
      synchronized (LedgerGrpc.class) {
        if ((getAuditAccountsMethod = LedgerGrpc.getAuditAccountsMethod) == null) {
          LedgerGrpc.getAuditAccountsMethod = getAuditAccountsMethod =
              io.grpc.MethodDescriptor.<amber.ledger.v1.LedgerOuterClass.AuditRequest, amber.ledger.v1.LedgerOuterClass.AuditResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AuditAccounts"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  amber.ledger.v1.LedgerOuterClass.AuditRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  amber.ledger.v1.LedgerOuterClass.AuditResponse.getDefaultInstance()))
              .setSchemaDescriptor(new LedgerMethodDescriptorSupplier("AuditAccounts"))
              .build();
        }
      }
    }
    return getAuditAccountsMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static LedgerStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<LedgerStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<LedgerStub>() {
        @java.lang.Override
        public LedgerStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new LedgerStub(channel, callOptions);
        }
      };
    return LedgerStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static LedgerBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<LedgerBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<LedgerBlockingStub>() {
        @java.lang.Override
        public LedgerBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new LedgerBlockingStub(channel, callOptions);
        }
      };
    return LedgerBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static LedgerFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<LedgerFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<LedgerFutureStub>() {
        @java.lang.Override
        public LedgerFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new LedgerFutureStub(channel, callOptions);
        }
      };
    return LedgerFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     * <pre>
     * --- Holds lifecycle ---------------------------------------------------
     * </pre>
     */
    default void holdFunds(amber.ledger.v1.LedgerOuterClass.HoldRequest request,
        io.grpc.stub.StreamObserver<amber.ledger.v1.LedgerOuterClass.HoldResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getHoldFundsMethod(), responseObserver);
    }

    /**
     */
    default void captureHold(amber.ledger.v1.LedgerOuterClass.CaptureRequest request,
        io.grpc.stub.StreamObserver<amber.ledger.v1.LedgerOuterClass.JournalResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCaptureHoldMethod(), responseObserver);
    }

    /**
     */
    default void releaseHold(amber.ledger.v1.LedgerOuterClass.ReleaseRequest request,
        io.grpc.stub.StreamObserver<amber.ledger.v1.LedgerOuterClass.JournalResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getReleaseHoldMethod(), responseObserver);
    }

    /**
     * <pre>
     * --- Money movement ----------------------------------------------------
     * </pre>
     */
    default void postTransfer(amber.ledger.v1.LedgerOuterClass.TransferRequest request,
        io.grpc.stub.StreamObserver<amber.ledger.v1.LedgerOuterClass.TransferResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getPostTransferMethod(), responseObserver);
    }

    /**
     */
    default void postFee(amber.ledger.v1.LedgerOuterClass.FeeRequest request,
        io.grpc.stub.StreamObserver<amber.ledger.v1.LedgerOuterClass.JournalResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getPostFeeMethod(), responseObserver);
    }

    /**
     */
    default void reverseJournal(amber.ledger.v1.LedgerOuterClass.ReverseRequest request,
        io.grpc.stub.StreamObserver<amber.ledger.v1.LedgerOuterClass.JournalResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getReverseJournalMethod(), responseObserver);
    }

    /**
     */
    default void refundPayment(amber.ledger.v1.LedgerOuterClass.RefundRequest request,
        io.grpc.stub.StreamObserver<amber.ledger.v1.LedgerOuterClass.JournalResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRefundPaymentMethod(), responseObserver);
    }

    /**
     * <pre>
     * --- Accounts &amp; statements ---------------------------------------------
     * </pre>
     */
    default void createAccount(amber.ledger.v1.LedgerOuterClass.CreateAccountRequest request,
        io.grpc.stub.StreamObserver<amber.ledger.v1.LedgerOuterClass.CreateAccountResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateAccountMethod(), responseObserver);
    }

    /**
     */
    default void getAccount(amber.ledger.v1.LedgerOuterClass.AccountRequest request,
        io.grpc.stub.StreamObserver<amber.ledger.v1.LedgerOuterClass.AccountResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetAccountMethod(), responseObserver);
    }

    /**
     */
    default void listEntries(amber.ledger.v1.LedgerOuterClass.ListRequest request,
        io.grpc.stub.StreamObserver<amber.ledger.v1.LedgerOuterClass.ListResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListEntriesMethod(), responseObserver);
    }

    /**
     * <pre>
     * --- Internal audit ----------------------------------------------------
     * </pre>
     */
    default void auditAccounts(amber.ledger.v1.LedgerOuterClass.AuditRequest request,
        io.grpc.stub.StreamObserver<amber.ledger.v1.LedgerOuterClass.AuditResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAuditAccountsMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Ledger.
   */
  public static abstract class LedgerImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return LedgerGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service Ledger.
   */
  public static final class LedgerStub
      extends io.grpc.stub.AbstractAsyncStub<LedgerStub> {
    private LedgerStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LedgerStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new LedgerStub(channel, callOptions);
    }

    /**
     * <pre>
     * --- Holds lifecycle ---------------------------------------------------
     * </pre>
     */
    public void holdFunds(amber.ledger.v1.LedgerOuterClass.HoldRequest request,
        io.grpc.stub.StreamObserver<amber.ledger.v1.LedgerOuterClass.HoldResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getHoldFundsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void captureHold(amber.ledger.v1.LedgerOuterClass.CaptureRequest request,
        io.grpc.stub.StreamObserver<amber.ledger.v1.LedgerOuterClass.JournalResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCaptureHoldMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void releaseHold(amber.ledger.v1.LedgerOuterClass.ReleaseRequest request,
        io.grpc.stub.StreamObserver<amber.ledger.v1.LedgerOuterClass.JournalResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getReleaseHoldMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * --- Money movement ----------------------------------------------------
     * </pre>
     */
    public void postTransfer(amber.ledger.v1.LedgerOuterClass.TransferRequest request,
        io.grpc.stub.StreamObserver<amber.ledger.v1.LedgerOuterClass.TransferResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getPostTransferMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void postFee(amber.ledger.v1.LedgerOuterClass.FeeRequest request,
        io.grpc.stub.StreamObserver<amber.ledger.v1.LedgerOuterClass.JournalResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getPostFeeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void reverseJournal(amber.ledger.v1.LedgerOuterClass.ReverseRequest request,
        io.grpc.stub.StreamObserver<amber.ledger.v1.LedgerOuterClass.JournalResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getReverseJournalMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void refundPayment(amber.ledger.v1.LedgerOuterClass.RefundRequest request,
        io.grpc.stub.StreamObserver<amber.ledger.v1.LedgerOuterClass.JournalResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRefundPaymentMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * --- Accounts &amp; statements ---------------------------------------------
     * </pre>
     */
    public void createAccount(amber.ledger.v1.LedgerOuterClass.CreateAccountRequest request,
        io.grpc.stub.StreamObserver<amber.ledger.v1.LedgerOuterClass.CreateAccountResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateAccountMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getAccount(amber.ledger.v1.LedgerOuterClass.AccountRequest request,
        io.grpc.stub.StreamObserver<amber.ledger.v1.LedgerOuterClass.AccountResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetAccountMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void listEntries(amber.ledger.v1.LedgerOuterClass.ListRequest request,
        io.grpc.stub.StreamObserver<amber.ledger.v1.LedgerOuterClass.ListResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getListEntriesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * --- Internal audit ----------------------------------------------------
     * </pre>
     */
    public void auditAccounts(amber.ledger.v1.LedgerOuterClass.AuditRequest request,
        io.grpc.stub.StreamObserver<amber.ledger.v1.LedgerOuterClass.AuditResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAuditAccountsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Ledger.
   */
  public static final class LedgerBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<LedgerBlockingStub> {
    private LedgerBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LedgerBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new LedgerBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * --- Holds lifecycle ---------------------------------------------------
     * </pre>
     */
    public amber.ledger.v1.LedgerOuterClass.HoldResponse holdFunds(amber.ledger.v1.LedgerOuterClass.HoldRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getHoldFundsMethod(), getCallOptions(), request);
    }

    /**
     */
    public amber.ledger.v1.LedgerOuterClass.JournalResponse captureHold(amber.ledger.v1.LedgerOuterClass.CaptureRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCaptureHoldMethod(), getCallOptions(), request);
    }

    /**
     */
    public amber.ledger.v1.LedgerOuterClass.JournalResponse releaseHold(amber.ledger.v1.LedgerOuterClass.ReleaseRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getReleaseHoldMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * --- Money movement ----------------------------------------------------
     * </pre>
     */
    public amber.ledger.v1.LedgerOuterClass.TransferResponse postTransfer(amber.ledger.v1.LedgerOuterClass.TransferRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getPostTransferMethod(), getCallOptions(), request);
    }

    /**
     */
    public amber.ledger.v1.LedgerOuterClass.JournalResponse postFee(amber.ledger.v1.LedgerOuterClass.FeeRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getPostFeeMethod(), getCallOptions(), request);
    }

    /**
     */
    public amber.ledger.v1.LedgerOuterClass.JournalResponse reverseJournal(amber.ledger.v1.LedgerOuterClass.ReverseRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getReverseJournalMethod(), getCallOptions(), request);
    }

    /**
     */
    public amber.ledger.v1.LedgerOuterClass.JournalResponse refundPayment(amber.ledger.v1.LedgerOuterClass.RefundRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRefundPaymentMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * --- Accounts &amp; statements ---------------------------------------------
     * </pre>
     */
    public amber.ledger.v1.LedgerOuterClass.CreateAccountResponse createAccount(amber.ledger.v1.LedgerOuterClass.CreateAccountRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateAccountMethod(), getCallOptions(), request);
    }

    /**
     */
    public amber.ledger.v1.LedgerOuterClass.AccountResponse getAccount(amber.ledger.v1.LedgerOuterClass.AccountRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetAccountMethod(), getCallOptions(), request);
    }

    /**
     */
    public amber.ledger.v1.LedgerOuterClass.ListResponse listEntries(amber.ledger.v1.LedgerOuterClass.ListRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListEntriesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * --- Internal audit ----------------------------------------------------
     * </pre>
     */
    public amber.ledger.v1.LedgerOuterClass.AuditResponse auditAccounts(amber.ledger.v1.LedgerOuterClass.AuditRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAuditAccountsMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Ledger.
   */
  public static final class LedgerFutureStub
      extends io.grpc.stub.AbstractFutureStub<LedgerFutureStub> {
    private LedgerFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LedgerFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new LedgerFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * --- Holds lifecycle ---------------------------------------------------
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<amber.ledger.v1.LedgerOuterClass.HoldResponse> holdFunds(
        amber.ledger.v1.LedgerOuterClass.HoldRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getHoldFundsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<amber.ledger.v1.LedgerOuterClass.JournalResponse> captureHold(
        amber.ledger.v1.LedgerOuterClass.CaptureRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCaptureHoldMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<amber.ledger.v1.LedgerOuterClass.JournalResponse> releaseHold(
        amber.ledger.v1.LedgerOuterClass.ReleaseRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getReleaseHoldMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * --- Money movement ----------------------------------------------------
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<amber.ledger.v1.LedgerOuterClass.TransferResponse> postTransfer(
        amber.ledger.v1.LedgerOuterClass.TransferRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getPostTransferMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<amber.ledger.v1.LedgerOuterClass.JournalResponse> postFee(
        amber.ledger.v1.LedgerOuterClass.FeeRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getPostFeeMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<amber.ledger.v1.LedgerOuterClass.JournalResponse> reverseJournal(
        amber.ledger.v1.LedgerOuterClass.ReverseRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getReverseJournalMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<amber.ledger.v1.LedgerOuterClass.JournalResponse> refundPayment(
        amber.ledger.v1.LedgerOuterClass.RefundRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRefundPaymentMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * --- Accounts &amp; statements ---------------------------------------------
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<amber.ledger.v1.LedgerOuterClass.CreateAccountResponse> createAccount(
        amber.ledger.v1.LedgerOuterClass.CreateAccountRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateAccountMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<amber.ledger.v1.LedgerOuterClass.AccountResponse> getAccount(
        amber.ledger.v1.LedgerOuterClass.AccountRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetAccountMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<amber.ledger.v1.LedgerOuterClass.ListResponse> listEntries(
        amber.ledger.v1.LedgerOuterClass.ListRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getListEntriesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * --- Internal audit ----------------------------------------------------
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<amber.ledger.v1.LedgerOuterClass.AuditResponse> auditAccounts(
        amber.ledger.v1.LedgerOuterClass.AuditRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAuditAccountsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_HOLD_FUNDS = 0;
  private static final int METHODID_CAPTURE_HOLD = 1;
  private static final int METHODID_RELEASE_HOLD = 2;
  private static final int METHODID_POST_TRANSFER = 3;
  private static final int METHODID_POST_FEE = 4;
  private static final int METHODID_REVERSE_JOURNAL = 5;
  private static final int METHODID_REFUND_PAYMENT = 6;
  private static final int METHODID_CREATE_ACCOUNT = 7;
  private static final int METHODID_GET_ACCOUNT = 8;
  private static final int METHODID_LIST_ENTRIES = 9;
  private static final int METHODID_AUDIT_ACCOUNTS = 10;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_HOLD_FUNDS:
          serviceImpl.holdFunds((amber.ledger.v1.LedgerOuterClass.HoldRequest) request,
              (io.grpc.stub.StreamObserver<amber.ledger.v1.LedgerOuterClass.HoldResponse>) responseObserver);
          break;
        case METHODID_CAPTURE_HOLD:
          serviceImpl.captureHold((amber.ledger.v1.LedgerOuterClass.CaptureRequest) request,
              (io.grpc.stub.StreamObserver<amber.ledger.v1.LedgerOuterClass.JournalResponse>) responseObserver);
          break;
        case METHODID_RELEASE_HOLD:
          serviceImpl.releaseHold((amber.ledger.v1.LedgerOuterClass.ReleaseRequest) request,
              (io.grpc.stub.StreamObserver<amber.ledger.v1.LedgerOuterClass.JournalResponse>) responseObserver);
          break;
        case METHODID_POST_TRANSFER:
          serviceImpl.postTransfer((amber.ledger.v1.LedgerOuterClass.TransferRequest) request,
              (io.grpc.stub.StreamObserver<amber.ledger.v1.LedgerOuterClass.TransferResponse>) responseObserver);
          break;
        case METHODID_POST_FEE:
          serviceImpl.postFee((amber.ledger.v1.LedgerOuterClass.FeeRequest) request,
              (io.grpc.stub.StreamObserver<amber.ledger.v1.LedgerOuterClass.JournalResponse>) responseObserver);
          break;
        case METHODID_REVERSE_JOURNAL:
          serviceImpl.reverseJournal((amber.ledger.v1.LedgerOuterClass.ReverseRequest) request,
              (io.grpc.stub.StreamObserver<amber.ledger.v1.LedgerOuterClass.JournalResponse>) responseObserver);
          break;
        case METHODID_REFUND_PAYMENT:
          serviceImpl.refundPayment((amber.ledger.v1.LedgerOuterClass.RefundRequest) request,
              (io.grpc.stub.StreamObserver<amber.ledger.v1.LedgerOuterClass.JournalResponse>) responseObserver);
          break;
        case METHODID_CREATE_ACCOUNT:
          serviceImpl.createAccount((amber.ledger.v1.LedgerOuterClass.CreateAccountRequest) request,
              (io.grpc.stub.StreamObserver<amber.ledger.v1.LedgerOuterClass.CreateAccountResponse>) responseObserver);
          break;
        case METHODID_GET_ACCOUNT:
          serviceImpl.getAccount((amber.ledger.v1.LedgerOuterClass.AccountRequest) request,
              (io.grpc.stub.StreamObserver<amber.ledger.v1.LedgerOuterClass.AccountResponse>) responseObserver);
          break;
        case METHODID_LIST_ENTRIES:
          serviceImpl.listEntries((amber.ledger.v1.LedgerOuterClass.ListRequest) request,
              (io.grpc.stub.StreamObserver<amber.ledger.v1.LedgerOuterClass.ListResponse>) responseObserver);
          break;
        case METHODID_AUDIT_ACCOUNTS:
          serviceImpl.auditAccounts((amber.ledger.v1.LedgerOuterClass.AuditRequest) request,
              (io.grpc.stub.StreamObserver<amber.ledger.v1.LedgerOuterClass.AuditResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getHoldFundsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              amber.ledger.v1.LedgerOuterClass.HoldRequest,
              amber.ledger.v1.LedgerOuterClass.HoldResponse>(
                service, METHODID_HOLD_FUNDS)))
        .addMethod(
          getCaptureHoldMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              amber.ledger.v1.LedgerOuterClass.CaptureRequest,
              amber.ledger.v1.LedgerOuterClass.JournalResponse>(
                service, METHODID_CAPTURE_HOLD)))
        .addMethod(
          getReleaseHoldMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              amber.ledger.v1.LedgerOuterClass.ReleaseRequest,
              amber.ledger.v1.LedgerOuterClass.JournalResponse>(
                service, METHODID_RELEASE_HOLD)))
        .addMethod(
          getPostTransferMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              amber.ledger.v1.LedgerOuterClass.TransferRequest,
              amber.ledger.v1.LedgerOuterClass.TransferResponse>(
                service, METHODID_POST_TRANSFER)))
        .addMethod(
          getPostFeeMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              amber.ledger.v1.LedgerOuterClass.FeeRequest,
              amber.ledger.v1.LedgerOuterClass.JournalResponse>(
                service, METHODID_POST_FEE)))
        .addMethod(
          getReverseJournalMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              amber.ledger.v1.LedgerOuterClass.ReverseRequest,
              amber.ledger.v1.LedgerOuterClass.JournalResponse>(
                service, METHODID_REVERSE_JOURNAL)))
        .addMethod(
          getRefundPaymentMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              amber.ledger.v1.LedgerOuterClass.RefundRequest,
              amber.ledger.v1.LedgerOuterClass.JournalResponse>(
                service, METHODID_REFUND_PAYMENT)))
        .addMethod(
          getCreateAccountMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              amber.ledger.v1.LedgerOuterClass.CreateAccountRequest,
              amber.ledger.v1.LedgerOuterClass.CreateAccountResponse>(
                service, METHODID_CREATE_ACCOUNT)))
        .addMethod(
          getGetAccountMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              amber.ledger.v1.LedgerOuterClass.AccountRequest,
              amber.ledger.v1.LedgerOuterClass.AccountResponse>(
                service, METHODID_GET_ACCOUNT)))
        .addMethod(
          getListEntriesMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              amber.ledger.v1.LedgerOuterClass.ListRequest,
              amber.ledger.v1.LedgerOuterClass.ListResponse>(
                service, METHODID_LIST_ENTRIES)))
        .addMethod(
          getAuditAccountsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              amber.ledger.v1.LedgerOuterClass.AuditRequest,
              amber.ledger.v1.LedgerOuterClass.AuditResponse>(
                service, METHODID_AUDIT_ACCOUNTS)))
        .build();
  }

  private static abstract class LedgerBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    LedgerBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return amber.ledger.v1.LedgerOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Ledger");
    }
  }

  private static final class LedgerFileDescriptorSupplier
      extends LedgerBaseDescriptorSupplier {
    LedgerFileDescriptorSupplier() {}
  }

  private static final class LedgerMethodDescriptorSupplier
      extends LedgerBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    LedgerMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (LedgerGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new LedgerFileDescriptorSupplier())
              .addMethod(getHoldFundsMethod())
              .addMethod(getCaptureHoldMethod())
              .addMethod(getReleaseHoldMethod())
              .addMethod(getPostTransferMethod())
              .addMethod(getPostFeeMethod())
              .addMethod(getReverseJournalMethod())
              .addMethod(getRefundPaymentMethod())
              .addMethod(getCreateAccountMethod())
              .addMethod(getGetAccountMethod())
              .addMethod(getListEntriesMethod())
              .addMethod(getAuditAccountsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
